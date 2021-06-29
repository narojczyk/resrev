package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwn.resrev.domain.model.Artefact;

import java.util.List;

public interface ArtefactRepository extends JpaRepository <Artefact,String>{
    List<Artefact> findAll();
//    List<Artefact> findAllByUserUUID(String userUuid);
}
