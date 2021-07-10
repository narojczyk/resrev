package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.Share;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ShareRepository;
import pl.jwn.resrev.domain.repository.UserRepository;

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
    public String shareArtefact(@RequestParam String artefactUuid,
                                @RequestParam String userToShareWith){
        // TODO sprawdzic czy nie udostepniam sobie
        // TODO sprawdzic czy udostepnienie istnieje
        Optional<User> optUser = userRepo.findByUsername(userToShareWith);
        if(optUser.isPresent()){
            shareRepo.save(new Share(artefactUuid, optUser.get().getUuid()));
            return "redirect:/resources/show/?uuid="+artefactUuid;
        }else{
            return "error";
        }

    }
}
