package rs.bg.ac.fon.elab.iteh.exit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
