package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwn.resrev.domain.model.Share;

import java.util.List;


public interface ShareRepository extends JpaRepository<Share,Long>  {
    List<Share> findAllBySharedWithUuid(String sharedWithUuid);
}
