package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwn.resrev.domain.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository <User,String> {
    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findByUuid(String uuid);

//    User findByUuid(String uuid);

}
