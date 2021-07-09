package pl.jwn.resrev.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.ShareRepository;
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
    private final ShareRepository shareRepo;

    public ArtefactService(ArtefactRepository artefactRepo, UserRepository userRepo, ShareRepository shareRepo) {
        this.artefactRepo = artefactRepo;
        this.userRepo = userRepo;
        this.shareRepo = shareRepo;
    }

    // Z tablicy udostępnień wyciągnij listę udostępnionych artefaktów do wyświetlenia
    public List<Artefact> fromUserShares(String userUUID){
        List<Artefact> artefacts = new ArrayList<>();
        Optional<User> optUser = userRepo.findByUuid(userUUID);
        if(optUser.isPresent()) {
            List<String> artefactUuids = shareRepo.findArtefactsUUIDsSharedForUser(userUUID);
            artefactUuids.forEach((uuid) -> {
                Optional<Artefact> optArtefact = artefactRepo.findByUuid(uuid);
                optArtefact.ifPresent(artefacts::add);
            });
        }
        return artefacts;
    }
}
