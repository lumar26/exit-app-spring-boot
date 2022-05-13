package rs.bg.ac.fon.elab.iteh.exit.model.keys;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class PerformanceKey implements Serializable {
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "performer_id")
    private Long performerId;

}
