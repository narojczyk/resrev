package pl.jwn.resrev.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jwn.resrev.domain.model.Comment;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.CommentRepository;
import pl.jwn.resrev.domain.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/comments")
@Slf4j
public class CommentCtrl {
    private final CommentRepository commentRepo;
    private final UserRepository userRepo;

    public CommentCtrl(CommentRepository commentRepo, UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/add")
    public String addComment(Principal principal,
                             @RequestParam String artefactUuid,
                             @RequestParam String commentMessage){
        Optional<User> optUser = userRepo.findByUsername(principal.getName());
        if(optUser.isPresent()) {
            commentRepo.save(
                    new Comment(artefactUuid, optUser.get().getUuid(), commentMessage));
            return "redirect:/resources/show/?uuid=" + artefactUuid;
        }
        return "redirect:/error";
    }
}
