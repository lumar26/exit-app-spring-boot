package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.service.EventService;
import rs.bg.ac.fon.elab.iteh.exit.service.PerformerService;
import rs.bg.ac.fon.elab.iteh.exit.service.StageService;
import rs.bg.ac.fon.elab.iteh.exit.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final PerformerService performerService;
    private final StageService stageService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, PerformerService performerService, StageService stageService, UserService userService) {
        this.eventService = eventService;
        this.performerService = performerService;
        this.stageService = stageService;
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(eventService.getEventById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Event> getAllEvent(){
        return eventService.getAllEvents();
    }

    @PostMapping
    public ResponseEntity<?> addNewEvent(@RequestBody Event newEvent){
        try {
            User user = userService.loadUserById(newEvent.getUser().getId());
            Stage stage = stageService.getByStageId(newEvent.getStage().getId());
            newEvent.setStage(stage);
            newEvent.setUser(user);
//            todo: check if performers exist and handle them
            return ResponseEntity.ok(eventService.saveNewEvent(newEvent));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id,
                             @RequestBody Event newEvent){
        try {
            User user = userService.loadUserById(newEvent.getUser().getId());
            Stage stage = stageService.getByStageId(newEvent.getStage().getId());
            newEvent.setStage(stage);
            newEvent.setUser(user);
            return ResponseEntity.ok(eventService.updateEvent(id, newEvent));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id){
        try {
            return ResponseEntity.ok(eventService.deleteEventById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
