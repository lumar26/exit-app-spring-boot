package rs.bg.ac.fon.elab.iteh.exit.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String password;
    private String username;
    private String email;
//    private String role;
}
