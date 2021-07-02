package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.UserRepository;
import pl.jwn.resrev.domain.service.SecurityService;

import java.util.Optional;

@Controller
@Slf4j
public class RegistrationCtrl {

    private final SecurityService security;
    private final UserRepository userRepository;

    public RegistrationCtrl(SecurityService security, UserRepository userRepository) {
        this.security = security;
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String prepareRegistrationPage(){
        return "security/registration";
    }

    @PostMapping("/registration")
    public String processRegistrationPage(User user, @RequestParam("passwdConfirmed") String passwdConfirmed){
        if(passwdConfirmed.equals(user.getPasswd())){
            security.registerUser(user);
            return "redirect:/login";
        }
        log.debug("Failed to register user "+user.getUsername()+". Confirmed password does not match.");
        return "security/registration";
    }

    @GetMapping("/passwd/reset")
    public String passwdResetConfirmation(Model model, @RequestParam("uuid") String uuid){
        Optional<User> optUser = userRepository.findByUuid(uuid);
        if(optUser.isPresent()){
            model.addAttribute("user", optUser.get());
            return "security/passwd-reset-confirmation";
        }
        return "error";
    }

    @PostMapping("/passwd/reset")
    public String passwdReset(@RequestParam("uuid") String uuid,
                              @RequestParam("passwd") String passwd,
                              @RequestParam("passwdConfirmed") String passwdConfirmed){
        if(passwd.equals(passwdConfirmed)){
            security.passwdReset(uuid,passwd);
            return "redirect:/logout";
        }
        return "redirect:/passwd/reset";
    }
}
