package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.ShareRepository;
import pl.jwn.resrev.domain.repository.UserRepository;
import pl.jwn.resrev.domain.service.ArtefactService;
import pl.jwn.resrev.domain.service.DataLoaderService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/resources")
@Slf4j
public class ArtefactCtrl {
    private final ArtefactRepository artefactRepo;
    private final UserRepository userRepo;
    private final DataLoaderService dataLoaderService;
    private final ArtefactService artefactService;

    public ArtefactCtrl(ArtefactRepository artefactRepo, UserRepository userRepo, ShareRepository shareRepo,
                        DataLoaderService dataLoaderService, ArtefactService artefactService) {
        this.artefactRepo = artefactRepo;
        this.userRepo = userRepo;
        this.dataLoaderService = dataLoaderService;
        this.artefactService = artefactService;
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
        Optional<User> optUser = userRepo.findByUsername(principal.getName());
        if(optUser.isPresent()) {
//            User user = optUser.get();
            String userUUID = optUser.get().getUuid();
            model.addAttribute("getResource", "myartefacts");
            // pobierz i wstaw do modelu listę artefaktów umieszczonych przez uzytkownika
            model.addAttribute("artefacts", artefactRepo.findAllByUserUuid(userUUID));
            // pobierz i wstaw do modelu listę artefaktów udostepnionych uzytkownikowi
            model.addAttribute("sharedArtefacts", artefactService.fromUserShares(userUUID));
            return "user/dashboard";
        }else{
            return "error";
        }
    }

}
