package rs.bg.ac.fon.elab.iteh.exit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private long userId;
    private String username;
    private String role;
    private String jwt;
}
