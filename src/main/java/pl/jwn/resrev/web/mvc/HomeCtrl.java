package pl.jwn.resrev.web.mvc;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeCtrl {
    // Takie pole jest generowane przez adnotację @Slf4j z lomboka
    // private static final Logger log = LoggerFactory.getLogger(UserFormController.class);

    @GetMapping("/")
    public String landingPage(Model model){
        return "landingpage";
    }
}
