package rs.bg.ac.fon.elab.iteh.exit.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty())
            throw new Exception("User with username = " + username + " does not exist.");
        optionalUser.get().getRole();
        return optionalUser.get();
    }
}
