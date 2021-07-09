package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.jwn.resrev.domain.model.Share;
import pl.jwn.resrev.utils.SQLTablesConstants;

import java.util.List;


public interface ShareRepository extends JpaRepository<Share,Long>  {
//    List<Share> findAllBySharedWithUuid(String sharedWithUuid);

    @Query(nativeQuery = true,
            value = "SELECT artefact_uuid FROM "+
                    SQLTablesConstants.SHARES + " WHERE shared_with_uuid = ?1 ")
    List<String> findArtefactsUUIDsSharedForUser(String userUuid);
}
