package rs.bg.ac.fon.elab.iteh.exit.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

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
        ADMIN, USER
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

    public User(String name, String password, String username, String email, UserRole role,
                Timestamp createdAt, Timestamp emailVerifiedAt, Timestamp updatedAt) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.emailVerifiedAt = emailVerifiedAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
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
