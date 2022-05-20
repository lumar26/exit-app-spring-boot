package rs.bg.ac.fon.elab.iteh.exit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.bg.ac.fon.elab.iteh.exit.model.Performance;
import rs.bg.ac.fon.elab.iteh.exit.model.keys.PerformanceKey;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, PerformanceKey> {
    void deleteAllByEvent_Id(Long eventId);
    List<Performance> getAllByEvent_Id(Long eventId);
}
