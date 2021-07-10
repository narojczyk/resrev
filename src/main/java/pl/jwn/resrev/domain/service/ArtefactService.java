package pl.jwn.resrev.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.model.Artefact;
import pl.jwn.resrev.domain.model.Comment;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.ArtefactRepository;
import pl.jwn.resrev.domain.repository.CommentRepository;
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
    private final CommentRepository commentRepo;

    public ArtefactService(ArtefactRepository artefactRepo, UserRepository userRepo, ShareRepository shareRepo, CommentRepository commentRepo) {
        this.artefactRepo = artefactRepo;
        this.userRepo = userRepo;
        this.shareRepo = shareRepo;
        this.commentRepo = commentRepo;
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

    public List<Comment> fromArtefactUuid(String artefactUUID){
        Optional<List<Comment>> optComments = commentRepo.findAllByArtefactUuid(artefactUUID);
        return (optComments.isPresent())? optComments.get() : new ArrayList<Comment>();
    }
}
