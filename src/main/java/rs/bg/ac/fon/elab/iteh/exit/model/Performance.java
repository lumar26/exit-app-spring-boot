package rs.bg.ac.fon.elab.iteh.exit.model;


import lombok.*;
import rs.bg.ac.fon.elab.iteh.exit.model.keys.PerformanceKey;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "event_performer")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Performance {
    @EmbeddedId
    private PerformanceKey primaryKey;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
