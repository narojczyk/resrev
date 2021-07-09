package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.UserRepository;
import pl.jwn.resrev.domain.service.DataLoaderService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserCtrl {
    // Takie pole jest generowane przez adnotację @Slf4j z lomboka
    // private static final Logger log = LoggerFactory.getLogger(UserFormController.class);

    private final UserRepository userRepo;
    private final DataLoaderService dataLoaderService;

    public UserCtrl(UserRepository userRepo, DataLoaderService dataLoaderService) {
        this.userRepo = userRepo;
        this.dataLoaderService = dataLoaderService;
    }

    @GetMapping("/testarea")
    public String list(Model model){
        dataLoaderService.remapUsersToModel(model);
        model.addAttribute("users", userRepo.findAll());
        return "user/list";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("getResource", "default");
        return "user/dashboard";
    }

    @GetMapping("/info")
    public String info(Principal principal, Model model){
        Optional<User> currentUser = userRepo.findByUsername(principal.getName());
        if(currentUser.isPresent()){
            model.addAttribute("getResource", "info");
            model.addAttribute("user", currentUser.get());
            return "user/dashboard";
        }else{
            return "error";
        }
    }

    @GetMapping("/modify")
    public String modifyForm(Principal principal, Model model){
        Optional<User> currentUser = userRepo.findByUsername(principal.getName());
        if(currentUser.isPresent()){
            model.addAttribute("getResource", "modifyUser");
            model.addAttribute("user", currentUser.get());
            return "user/dashboard";
        }else{
            return "error";
        }
    }

    @Transactional
    @PostMapping("/modify")
    public String modifyUser(@RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("userUuid") String uuid){
        Optional <User> optUser = userRepo.findByUuid(uuid);
        // update login
        if(optUser.isPresent() && username.length()>0){
            optUser.get().setUsername(username);
            return "redirect:/logout";
        }
        // update email
        if(optUser.isPresent() && email.length()>0){
            optUser.get().setEmail(email);
            return "redirect:/user/info";
        }
        return "error";
    }



}
