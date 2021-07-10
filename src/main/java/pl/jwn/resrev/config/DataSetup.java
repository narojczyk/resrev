package pl.jwn.resrev.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.Comment;
import pl.jwn.resrev.domain.model.Share;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.CommentRepository;
import pl.jwn.resrev.domain.repository.ShareRepository;
import pl.jwn.resrev.domain.repository.UserRepository;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class DataSetup {
    private AtomicBoolean alreadyRun = new AtomicBoolean(false);
    private AtomicBoolean setAdminAccount = new AtomicBoolean(false);

    private final UserRepository userRepository;
    private final ArtefactRepository artefactRepository;
    private final CommentRepository commentRepository;
    private final ShareRepository shareRepository;

    public DataSetup(UserRepository userRepository, ArtefactRepository artefactRepository,
                     CommentRepository commentRepository, ShareRepository shareRepository) {
        this.userRepository = userRepository;
        this.artefactRepository = artefactRepository;
        this.commentRepository = commentRepository;
        this.shareRepository = shareRepository;
    }

    //TODO Czemu rozbijać to na dwie metody?
    @EventListener
    @Transactional
    public void initData(ContextRefreshedEvent event) {
        if (!setAdminAccount.getAndSet(true)) {
            userRepository.save(new User("admin", "admin@localhost.com","{noop}admin", "ROLE_ADMIN"));
        }
    }

    @EventListener
    @Transactional
    public void testData(ContextRefreshedEvent event) {
        if (!alreadyRun.getAndSet(true)) {
            log.debug("~-~".repeat(20));
            log.debug("Fresh start");
            log.debug("~-~".repeat(20));
            log.debug("Putting test data to sql (users)");
            User kww = new User("kww", "kww@costam.pl","{noop}x", "ROLE_USER");
            User jwn = new User("jwn", "jwn@costam.pl","{noop}x", "ROLE_USER");
            User kvt = new User("kvt", "kvt@costam.pl","{noop}x", "ROLE_USER");
            userRepository.save(jwn);
            userRepository.save(kww);
            userRepository.save(kvt);
            log.debug("3 users added");
            log.debug("~-~".repeat(20));

            log.debug("Putting test data to sql (artefacts)");
            Artefact art1 = new Artefact(kww.getUuid(),"cos w kontenerze 1", "report", "pdf", "zestawienie super ważnych danych");
            Artefact art2 = new Artefact(kww.getUuid(),"cos w kontenerze 2", "article", "pdf", "pdf opublikowanego artykułu");
            Artefact art3 = new Artefact(jwn.getUuid(),"cos w kontenerze 3", "plot", "png", "wykres zawartości cukru w cukrze");
            Artefact art4 = new Artefact(jwn.getUuid(),"cos w kontenerze 4", "article", "pdf", "draft publikacji");
            Artefact art5 = new Artefact(jwn.getUuid(),"cos w kontenerze 5", "archiwum", "zip", "dane źródłowe");
            Artefact art6 = new Artefact(kvt.getUuid(),"cos w kontenerze 6", "archiwum", "zip", "dane z ostatniej symulacji");
            artefactRepository.save(art1);
            artefactRepository.save(art2);
            artefactRepository.save(art3);
            artefactRepository.save(art4);
            artefactRepository.save(art5);
            artefactRepository.save(art6);
            log.debug("6 artefacts added");

            log.debug("Putting test Comments to artefacts");
            commentRepository.save(new Comment(art1.getUuid(), jwn.getUuid(), "super wyniki"));
            commentRepository.save(new Comment(art3.getUuid(), kww.getUuid(), "moj pies zrobił by to lepiej"));
            commentRepository.save(new Comment(art3.getUuid(), kvt.getUuid(), "dno, muł i wodorosty"));
            commentRepository.save(new Comment(art4.getUuid(), kvt.getUuid(), "może coś z tego będzie"));
            log.debug("4 comments added");

            log.debug("sharing selected artefacts");
            shareRepository.save(new Share(art3.getUuid(), kww.getUuid()));
            shareRepository.save(new Share(art3.getUuid(), kvt.getUuid()));
            shareRepository.save(new Share(art4.getUuid(), kvt.getUuid()));
            shareRepository.save(new Share(art1.getUuid(), jwn.getUuid()));
            shareRepository.save(new Share(art6.getUuid(), jwn.getUuid()));
            log.debug("5 artefact sheares added");
        }
    }
}