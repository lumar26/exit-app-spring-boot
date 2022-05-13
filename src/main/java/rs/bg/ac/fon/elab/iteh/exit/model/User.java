package rs.bg.ac.fon.elab.iteh.exit.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    private String role;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "email_verified_at")
    private Timestamp emailVerifiedAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
