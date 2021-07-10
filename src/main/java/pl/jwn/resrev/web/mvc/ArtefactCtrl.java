package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.ShareRepository;
import pl.jwn.resrev.domain.repository.UserRepository;
import pl.jwn.resrev.domain.service.ArtefactService;
import pl.jwn.resrev.domain.service.DataLoaderService;
import pl.jwn.resrev.domain.service.DocumentTransferService;

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
    private final DocumentTransferService documentTransferService;

    public ArtefactCtrl(ArtefactRepository artefactRepo, UserRepository userRepo, ShareRepository shareRepo,
                        DataLoaderService dataLoaderService, ArtefactService artefactService, DocumentTransferService documentTransferService) {
        this.artefactRepo = artefactRepo;
        this.userRepo = userRepo;
        this.dataLoaderService = dataLoaderService;
        this.artefactService = artefactService;
        this.documentTransferService = documentTransferService;
    }

//    @GetMapping("/share")
//    public String artefactSharingForm(Model model,
//                                      @RequestParam(required = false) String share){
//        model.addAttribute("getResource", "artefactDetails");
//        // Wyświetl formularz do udostępniania artefaktu
//        if(share!=null && share.equals("artefactShareForm")){
//            model.addAttribute("artefactShareForm", share);
//        }
//        return "user/dashboard";
//    }

    @GetMapping("/create")
    public String artefactCreationForm(Model model){
        model.addAttribute("getResource", "artefactCreateForm");
        return "user/dashboard";
    }

    @PostMapping("/create")
    public String createArtefact(Principal principal,
                                 @RequestParam("document") MultipartFile mpf,
                                 @RequestParam String type,
                                 @RequestParam String description){
        Artefact artefact = new Artefact();
        Optional<User> optUser = userRepo.findByUsername(principal.getName());
        if(optUser.isPresent()){
            // Ustaw właściciela
            artefact.setUserUuid(optUser.get().getUuid());
            artefact.setContainer("tymczasowo - pole do wywalenia - zastąpiono prze upload pliku");

            // ustawić parametry artefaktu zebrane z pliku
            artefact = documentTransferService.uploadDocument(mpf, artefact);

            // ustawić parametry artefaktu zebrane z formularza
            artefact.setDescription(description);
            artefact.setType(type);

            // zapisz artefakt w bazie
            artefactRepo.save(artefact);
            // dodać widok z komentarzem o sukciesie uploadu
            return "redirect:/resources/userartefacts";
        }else{
            return "error";
        }
    }

    @GetMapping("/show")
    public String showArtefact(Model model, @RequestParam String uuid,
                               @RequestParam(required = false) String insert){
        dataLoaderService.remapUsersToModel(model);
        Optional<Artefact> art = artefactRepo.findByUuid(uuid);
        if(art.isPresent()) {
            model.addAttribute("getResource", "artefactDetails");
            model.addAttribute("artefact", art.get());
            // Zaczytaj komentarze do artefaktu
            model.addAttribute("comments",
                    artefactService.fromArtefactUuid(art.get().getUuid()));
            // Wyświetl formularz do dodawania komentarza do danego artefaktu
            if(insert!=null){
                model.addAttribute( insert, insert);
            }
            return "user/dashboard";
        }
        return "error";
    }

    @GetMapping("/userartefacts")
    public String userartefacs(Principal principal, Model model){
        dataLoaderService.remapUsersToModel(model);
        Optional<User> optUser = userRepo.findByUsername(principal.getName());
        if(optUser.isPresent()) {
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
