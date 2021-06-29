package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jwn.resrev.domain.repository.ArtefactRepository;

@Controller
@RequestMapping("/resources")
@Slf4j
public class ArtefactCtrl {
    private final ArtefactRepository artefactRepo;

    public ArtefactCtrl(ArtefactRepository artefactRepo) {
        this.artefactRepo = artefactRepo;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("artefacts", artefactRepo.findAll());
        return "artefacts/list";
    }
}
