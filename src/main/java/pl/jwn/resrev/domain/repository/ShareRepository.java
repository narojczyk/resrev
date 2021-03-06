package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.jwn.resrev.domain.model.Share;
import pl.jwn.resrev.utils.SQLTablesConstants;

import java.util.List;


public interface ShareRepository extends JpaRepository<Share,Long>  {

    @Query(nativeQuery = true,
            value = "SELECT artefact_uuid FROM "+
                    SQLTablesConstants.SHARES + " WHERE shared_with_uuid = ?1 ")
    List<String> findArtefactsUUIDsSharedForUser(String userUuid);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(id) FROM " +
                    SQLTablesConstants.SHARES + " WHERE artefact_uuid = ?1 AND shared_with_uuid = ?2 ")
    int countExactMatchesOfUuidPairs(String artefactUuid, String sharedWithUuid);
}
