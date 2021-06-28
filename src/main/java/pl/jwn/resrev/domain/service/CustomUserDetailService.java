package pl.jwn.resrev.domain.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.jwn.resrev.domain.model.User;
import pl.jwn.resrev.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.map(user->new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPasswd(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        )).orElseThrow(()->new UsernameNotFoundException("Username "+ username + " not found"));
    }
}
