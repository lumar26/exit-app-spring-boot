package rs.bg.ac.fon.elab.iteh.exit.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class User implements UserDetails {

    public enum UserRole {
        ROLE_ADMIN, ROLE_USER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String username;
    private String email;
    private UserRole role;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "email_verified_at")
    private Timestamp emailVerifiedAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role.equals(UserRole.ROLE_ADMIN))
            return List.of((GrantedAuthority) () -> "ROLE_ADMIN");
        return List.of((GrantedAuthority) () -> "ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
