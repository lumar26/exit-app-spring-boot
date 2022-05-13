package rs.bg.ac.fon.elab.iteh.exit.model.dto;

import lombok.Data;

@Data
public class AuthenticatedUserDto {
    private String email;
    private String password;
    private String accessToken;
    private String tokenType;
}
