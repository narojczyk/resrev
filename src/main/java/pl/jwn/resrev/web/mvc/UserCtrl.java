package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.UserRepository;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserCtrl {
    // Takie pole jest generowane przez adnotację @Slf4j z lomboka
    // private static final Logger log = LoggerFactory.getLogger(UserFormController.class);

    private final UserRepository userRepo;

    public UserCtrl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "user/list";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("getResource", "default");
        return "user/dashboard";
    }

    @GetMapping("/info")
    public String panel(Principal principal, Model model){
        Optional<User> currentUser = userRepo.findByUsername(principal.getName());
        if(currentUser.isPresent()){
            model.addAttribute("user", currentUser.get());
            model.addAttribute("getResource", "info");
            return "user/dashboard";
        }else{
            return "error";
        }
    }

}
