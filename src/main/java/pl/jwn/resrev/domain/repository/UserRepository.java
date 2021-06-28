package pl.jwn.resrev.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jwn.resrev.domain.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository <User,String> {
    List<User> findAll();
}
