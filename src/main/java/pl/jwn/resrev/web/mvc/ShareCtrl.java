package pl.jwn.resrev.web.mvc;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.Share;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ShareRepository;
import pl.jwn.resrev.domain.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/share")
@Slf4j
public class ShareCtrl {
    private final ShareRepository shareRepo;
    private final UserRepository userRepo;

    public ShareCtrl(ShareRepository shareRepo, UserRepository userRepo) {
        this.shareRepo = shareRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/artefact")
    public String shareArtefact(Principal principal,
                                @RequestParam String artefactUuid,
                                @RequestParam String userToShareWith){
        Optional<User> optUser = userRepo.findByUsername(userToShareWith);
        if(optUser.isPresent()){
            String uuidToShareWith = optUser.get().getUuid();
            // Jeżeli znajdzie parę artefactUUID i userUUID to udostępnienie już istnieje
            boolean shareExists =
                    (shareRepo.countExactMatchesOfUuidPairs(artefactUuid, uuidToShareWith) != 0);
            // Sprawdz UUID aby nie udostępnić samemu sobie
            boolean shareToMySelf = userToShareWith.equals(principal.getName());
            // Jak wsio bangla zapisz udostępnienie w SQL
            if(!shareExists && !shareToMySelf) {
                shareRepo.save(new Share(artefactUuid, uuidToShareWith));
            }
            return "redirect:/resources/show/?uuid="+artefactUuid;
        }else{
            return "error";
        }

    }
}
