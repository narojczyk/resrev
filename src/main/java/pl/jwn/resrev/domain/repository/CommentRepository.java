package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwn.resrev.domain.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<List<Comment>> findAllByArtefactUuid(String artefactUuid);
}
