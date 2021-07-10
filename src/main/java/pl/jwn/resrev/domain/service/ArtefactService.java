package pl.jwn.resrev.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.Share;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ArtefactService {
    private final ArtefactRepository artefactRepo;
    private final UserRepository userRepo;

    public ArtefactService(ArtefactRepository artefactRepo, UserRepository userRepo) {
        this.artefactRepo = artefactRepo;
        this.userRepo = userRepo;
    }

    // Przetłumacz listę udostępnień użytkownika na listę artefaktów do wyświetlenia
    public List<Artefact> fromUserShares(String userUUID){
        List<Artefact> artefacts = new ArrayList<>();
        Optional<User> optUser = userRepo.findByUuid(userUUID);
        if(optUser.isPresent()) {
            List<Share> userShares = optUser.get().getSharesData();
            if (userShares.isEmpty()) {
                //TODO Po co XXX, czyżby porno w kodzie???
                log.debug("XXX shares data empty");
            } else {
                log.debug("XXX shares found");
                log.debug(userShares.toString());
            }
            if (!userShares.isEmpty()) {
                List<String> artefactUuids = new ArrayList<>();
                //TODO Dzielić na metody zamiast opisywać komentarzami

                // wyciągnij sygnatury artefaktów
                userShares.forEach((s) -> artefactUuids.add(s.getArtefactUuid()));
                // dla każdej sygnatury pobierz artefakt i dodaj do listy udostępnionych zasobów
                artefactUuids.forEach((uuid) -> {
                    Optional<Artefact> optArtefact = artefactRepo.findByUuid(uuid);
                    optArtefact.ifPresent(artefacts::add);
                });
            }
        }
        return artefacts;
    }
}
