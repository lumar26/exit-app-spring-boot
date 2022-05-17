package rs.bg.ac.fon.elab.iteh.exit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.model.Performance;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;
import rs.bg.ac.fon.elab.iteh.exit.model.keys.PerformanceKey;
import rs.bg.ac.fon.elab.iteh.exit.repository.PerformanceRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceService {
    private final PerformanceRepository repository;

    @Autowired
    public PerformanceService(PerformanceRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public List<Performance> savePerformancesForEvent(List<Performer> performers, Event event) throws Exception {
        List<Performance> result = new ArrayList<>(performers.size());
        performers.forEach(performer -> {
            Performance performance  = new Performance(event, performer, Timestamp.valueOf(LocalDateTime.now()));
            repository.save(performance);
            result.add(performance);
        });
        return result;
    }
}
