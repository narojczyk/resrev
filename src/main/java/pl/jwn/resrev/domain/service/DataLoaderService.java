package pl.jwn.resrev.domain.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DataLoaderService {
    private final UserRepository userRepository;

    public DataLoaderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void remapUsersToModel(Model model){
        List<User> users = userRepository.findAll();
        HashMap<String,String> userNamesMap = new HashMap<>();
        if(!users.isEmpty()) {
            users.stream()
                    .filter(u->u.getRole().contains("USER"))
                    .forEach(u -> userNamesMap.put(u.getUuid(), u.getUsername()));
            model.addAttribute("userNamesMap", userNamesMap);
        }
    }
}
