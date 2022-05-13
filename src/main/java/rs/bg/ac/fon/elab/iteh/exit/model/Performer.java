package rs.bg.ac.fon.elab.iteh.exit.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "performers")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String nick;
    private String genre;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
