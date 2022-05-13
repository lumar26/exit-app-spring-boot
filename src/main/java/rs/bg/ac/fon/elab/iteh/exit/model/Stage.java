package rs.bg.ac.fon.elab.iteh.exit.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stages")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;
    private String location;
    private String sponsor;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
