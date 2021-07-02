package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.Share;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.ShareRepository;
import pl.jwn.resrev.domain.repository.UserRepository;
import pl.jwn.resrev.domain.service.DataLoaderService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/resources")
@Slf4j
public class ArtefactCtrl {
    private final ArtefactRepository artefactRepo;
    private final UserRepository userRepo;
    private final ShareRepository shareRepo;
    private final DataLoaderService dataLoaderService;

    public ArtefactCtrl(ArtefactRepository artefactRepo, UserRepository userRepo, ShareRepository shareRepo,
                        DataLoaderService dataLoaderService) {
        this.artefactRepo = artefactRepo;
        this.userRepo = userRepo;
        this.shareRepo = shareRepo;
        this.dataLoaderService = dataLoaderService;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("artefacts", artefactRepo.findAll());
        return "artefacts/list";
    }

    @GetMapping("/show")
    public String showArtefact(Model model, @RequestParam String uuid ){
        dataLoaderService.remapUsersToModel(model);
        Optional<Artefact> art = artefactRepo.findByUuid(uuid);
        if(art.isPresent()) {
            model.addAttribute("getResource", "artefactDetails");
            model.addAttribute("artefact", art.get());
            return "user/dashboard";
        }
        return "error";
    }

    @GetMapping("/userartefacts")
    public String userartefacs(Principal principal, Model model){
        dataLoaderService.remapUsersToModel(model);
        Optional<User> currentUser = userRepo.findByUsername(principal.getName());
        if(currentUser.isPresent()) {
            model.addAttribute("getResource", "myartefacts");
            String currentUserUuid = currentUser.get().getUuid();
            // pobierz i wstaw do modelu listę artefaktów umieszczonych przez uzytkownika
            model.addAttribute("artefacts", artefactRepo.findAllByUserUuid(currentUserUuid));
            // pobierz liste informacji o udostępnionych użytkownikowi artefaktach
            List<Share> sharedInfo = shareRepo.findAllBySharedWithUuid(currentUserUuid);
            if(!sharedInfo.isEmpty()){
                List<String> sharedUuids = new ArrayList<>();
                // jeżeli jest nie-pusta, wyciągnij sygnatury artefaktów
                sharedInfo.stream().forEach((s)->sharedUuids.add(s.getArtefactUuid()));
                List<Artefact> sharedArtefacts = new ArrayList<>();
                // dla każdej sygnatury pobierz artefakt i dodaj do listy udostępnionych zasobów
                sharedUuids.stream().forEach((uuid)->{
                    Optional<Artefact> art = artefactRepo.findByUuid(uuid);
                    if(art.isPresent()) {
                        sharedArtefacts.add(art.get());
                    }
                });
                // wstaw do modelu liste artefaktów udostępnionych dla użytkownika
                model.addAttribute("sharedArtefacts", sharedArtefacts);
            }
            return "user/dashboard";
        }else{
            return "error";
        }
    }
}
