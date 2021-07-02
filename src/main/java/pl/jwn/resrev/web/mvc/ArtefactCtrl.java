package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/resources")
@Slf4j
public class ArtefactCtrl {
    private final ArtefactRepository artefactRepo;
    private final UserRepository userRepo;

    public ArtefactCtrl(ArtefactRepository artefactRepo, UserRepository userRepo) {
        this.artefactRepo = artefactRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("artefacts", artefactRepo.findAll());
        return "artefacts/list";
    }

    @GetMapping("/userartefacts")
    public String userartefacs(Principal principal, Model model){
        Optional<User> currentUser = userRepo.findByUsername(principal.getName());
        if(currentUser.isPresent()) {
            model.addAttribute("getResource", "myartefacts");
            model.addAttribute("artefacts", artefactRepo.findAllByUserUuid(currentUser.get().getUuid()));
            return "user/dashboard";
        }else{
            return "error";
        }
    }
}
