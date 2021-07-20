package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwn.resrev.domain.model.Artefact;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface ArtefactRepository extends JpaRepository <Artefact,String>{

    //TODO Taka metoda już istnieje w JpaRepository
    List<Artefact> findAll();

    List<Artefact> findAllByUserUuid(String userUuid);

    //TODO To jest to samo co findById(String uuid)
    Optional<Artefact> findByUuid(String uuid);
}
