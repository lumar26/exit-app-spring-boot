package rs.bg.ac.fon.elab.iteh.exit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.repository.EventRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repository;
    private final PerformanceService performanceService;

    @Autowired
    public EventService(EventRepository repository, PerformanceService performanceService) {
        this.repository = repository;
        this.performanceService = performanceService;
    }

    public Event getEventById(Long id) throws Exception {
        Optional<Event> optionalEvent = repository.findById(id);
        if (optionalEvent.isEmpty())
            throw new Exception("Culd not find event with id = " + id + ".");
        return optionalEvent.get();
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    @Transactional
    public Event saveNewEvent(Event newEvent) throws Exception {
        return repository.save(newEvent);
    }

    @Transactional
    public Event updateEvent(Long id, Event newEvent) throws Exception {
        Optional<Event> optionalEvent = repository.findById(id);
        if (optionalEvent.isEmpty())
            throw new Exception("Cannot update event. Event with id = " + id + "does not exist");
        newEvent.setId(id);
//        ovo smemo da uradimo jer je jwt token vec proveren od strane Spring Security
        newEvent.getUser().setRole(User.Role.ROLE_ADMIN);
        return repository.save(newEvent);
    }

    @Transactional
    public Event deleteEventById(Long id) throws Exception {
        Optional<Event> optionalStage = repository.findById(id);
        if (optionalStage.isEmpty())
            throw new Exception("Cannot delete event. Event with id = " + id + "does not exist");
        repository.deleteById(id);
        return optionalStage.get();
    }
}
