package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.service.SecurityService;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationCtrl {

    private final SecurityService security;

    public RegistrationCtrl(SecurityService security) {
        this.security = security;
    }

    @GetMapping
    public String prepareRegistrationPage(){
        return "security/registration";
    }

    @PostMapping
    public String processRegistrationPage(User user, @RequestParam("passwdConfirmed") String passwdConfirmed){
        if(passwdConfirmed.equals(user.getPasswd())){
            security.registerUser(user);
            return "redirect:/";
        }
        log.debug("Failed to register user "+user.getUsername()+". Confirmed password does not match.");
        return "security/registration";
    }
}
