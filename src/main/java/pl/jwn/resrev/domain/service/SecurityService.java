package pl.jwn.resrev.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.UserRepository;

@Service
@Transactional
public class SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user){
        String encodedPasswd = passwordEncoder.encode(user.getPasswd());
        user.setPasswd(encodedPasswd);
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
