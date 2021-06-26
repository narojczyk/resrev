package pl.jwn.resrev.domain.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@Transactional
public class UserDao {
     @PersistenceContext
     EntityManager em;
     
     public void add(User user) {
          em.persist(user);
     }

     public User update(User user) {
          return em.merge(user);
     }
     
     public List<User> findAll(){ 
          return em.createQuery("select u from User u").getResultList();
     }
}
