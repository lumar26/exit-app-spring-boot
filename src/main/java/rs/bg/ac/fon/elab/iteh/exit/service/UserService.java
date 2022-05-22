package rs.bg.ac.fon.elab.iteh.exit.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.dto.RegisterRequest;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty())
            throw new Exception("User with username = " + username + " does not exist.");
        optionalUser.get().getRole();
        return optionalUser.get();
    }

    public User loadUserById(Long id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new Exception("User with id = " + id + " does not exist.");
        optionalUser.get().getRole();
        return optionalUser.get();
    }

    public User registerUser(RegisterRequest accountDto) throws Exception {
        if (accountDto == null) throw new IllegalStateException("Could not register user. Invalid request object.");
        Optional<User> found = userRepository.findUserByUsernameOrEmail(accountDto.getUsername(), accountDto.getEmail());
        if (found.isPresent()) {
            throw new Exception("There is an account with that email address:" + accountDto.getEmail());
        }
        User user = new User(0L,
                accountDto.getName(),
                passwordEncoder.encode(accountDto.getPassword()),
                accountDto.getUsername(),
                accountDto.getEmail(),
                User.Role.ROLE_USER,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));

        return userRepository.save(user);
    }
}
