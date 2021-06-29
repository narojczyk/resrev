package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwn.resrev.domain.model.Artefact;

public interface ArtefactRepository extends JpaRepository <Artefact,String>{

}
