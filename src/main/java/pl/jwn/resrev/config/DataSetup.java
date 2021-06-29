package pl.jwn.resrev.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.UserRepository;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class DataSetup {
    private AtomicBoolean alreadyRun = new AtomicBoolean(false);
    private AtomicBoolean setAdminAccount = new AtomicBoolean(false);

    private final UserRepository userRepository;
    private final ArtefactRepository artefactRepository;

    public DataSetup(UserRepository userRepository, ArtefactRepository artefactRepository) {
        this.userRepository = userRepository;
        this.artefactRepository = artefactRepository;
    }

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
            User kww = new User("kww", "kww@costam.pl","{noop}def", "ROLE_USER");
            User jwn = new User("jwn", "jwn@costam.pl","{noop}abc", "ROLE_USER");
            User kvt = new User("kvt", "kvt@costam.pl","{noop}ghi", "ROLE_USER");
            userRepository.save(jwn);
            userRepository.save(kww);
            userRepository.save(kww);
            log.debug("3 users added");
            log.debug("~-~".repeat(20));

            log.debug("Putting test data to sql (artefacts)");
            artefactRepository.save(new Artefact(kww.getUuid(),"cos w kontenerze 1", "report", "pdf", "zestawienie super ważnych danych"));
            artefactRepository.save(new Artefact(kww.getUuid(),"cos w kontenerze 2", "article", "pdf", "pdf opublikowanego artykułu"));
            artefactRepository.save(new Artefact(jwn.getUuid(),"cos w kontenerze 3", "plot", "png", "wykres zawartości cukru w cukrze"));
            artefactRepository.save(new Artefact(kvt.getUuid(),"cos w kontenerze 4", "archiwum", "zip", "dane z ostatniej symulacji"));
            log.debug("4 artefacts added");
        }
    }
}