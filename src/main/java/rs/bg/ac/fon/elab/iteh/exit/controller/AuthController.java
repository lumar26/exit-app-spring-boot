package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.dto.AuthenticationResponse;
import rs.bg.ac.fon.elab.iteh.exit.dto.LoginRequest;
import rs.bg.ac.fon.elab.iteh.exit.dto.RegisterRequest;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.service.UserService;
import rs.bg.ac.fon.elab.iteh.exit.security.util.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AuthController {

    private final AuthenticationManager manager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager manager, UserService userService, JwtUtil jwtUtil) {
        this.manager = manager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {

//        autentifikacija standardnog tokena za username i password
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            System.out.println("Could not authenticate user with auth manager");
            throw new IllegalStateException("Bad credentials for login");
        }
//        vracanje korisnika na osnovu username
        final User user = userService.loadUserByUsername(loginRequest.getUsername());
//        generisanje jwt tokena
        final String jwt = jwtUtil.generateToken(user);
        return new AuthenticationResponse(user.getId(), user.getUsername(), user.getRole().name(), jwt);
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:8100")
    public AuthenticationResponse register(@RequestBody RegisterRequest user) {
        return null;
    }

}
