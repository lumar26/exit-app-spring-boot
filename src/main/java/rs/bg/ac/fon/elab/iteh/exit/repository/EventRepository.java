package rs.bg.ac.fon.elab.iteh.exit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
