package rs.bg.ac.fon.elab.iteh.exit.model.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String name;
    private String password;
    private String email;
    private String role;
}
