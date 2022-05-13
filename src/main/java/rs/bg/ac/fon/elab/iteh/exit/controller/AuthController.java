package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.bg.ac.fon.elab.iteh.exit.model.dto.AuthenticatedUserDto;
import rs.bg.ac.fon.elab.iteh.exit.model.dto.UserLoginDto;
import rs.bg.ac.fon.elab.iteh.exit.model.dto.UserRegisterDto;

@RestController
public class AuthController {
    @PostMapping("/login")
    public AuthenticatedUserDto login(@RequestBody UserLoginDto user){
        return null;
    }

    @PostMapping("/register")
    public AuthenticatedUserDto login(@RequestBody UserRegisterDto user){
        return null;
    }

}
