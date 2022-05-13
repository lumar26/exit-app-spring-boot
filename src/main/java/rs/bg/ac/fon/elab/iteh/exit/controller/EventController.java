package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @GetMapping("{id}")
    public Event getEventById(@PathVariable Long id){
        return null;
    }

    @GetMapping
    public List<Event> getAllEvent(){
        return List.of();
    }

    @PostMapping
    public Event addNewEvent(@RequestBody Event newPerformer){
        return null;
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id,
                             @RequestBody Event newPerformer){
        return null;
    }

    @DeleteMapping("/{id}")
    public Event deleteEvent(@PathVariable Long id){
        return null;
    }
}
