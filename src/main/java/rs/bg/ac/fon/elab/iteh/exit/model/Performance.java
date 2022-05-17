package rs.bg.ac.fon.elab.iteh.exit.model;


import lombok.*;
import rs.bg.ac.fon.elab.iteh.exit.model.keys.PerformanceKey;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private PerformanceKey primaryKey = new PerformanceKey();

    @ManyToOne
    @MapsId("eventId")
//    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @MapsId("performerId")
//    @JoinColumn(name = "performer_id")
    private Performer performer;

    @Column(name = "created_at")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Performance(Event event, Performer performer, Timestamp updatedAt) {
        this.event = event;
        this.performer = performer;
        this.updatedAt = updatedAt;
    }
}
