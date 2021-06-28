package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jwn.resrev.domain.repository.UserRepository;

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

    @GetMapping("/login")
    public String loginForm(Model model){
//        model.addAttribute("users", userDao.findAll());
        return "user/login";
    }

    @PostMapping("/login")
    public String loginFormProcessing(Model model){
//        model.addAttribute("users", userDao.findAll());
        return "landingpage";
    }

    @GetMapping("/register")
    public String register(Model model){
//        model.addAttribute("users", userDao.findAll());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerFormProcessing(Model model){
//        model.addAttribute("users", userDao.findAll());
        return "landingpage";
    }
}
