package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.dto.CreateEventDto;
import rs.bg.ac.fon.elab.iteh.exit.mapper.EventMapper;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.service.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final PerformerService performerService;
    private final StageService stageService;
    private final UserService userService;
    private final PerformanceService performanceService;

    @Autowired
    public EventController(EventService eventService, PerformerService performerService, StageService stageService, UserService userService, PerformanceService performanceService) {
        this.eventService = eventService;
        this.performerService = performerService;
        this.stageService = stageService;
        this.userService = userService;
        this.performanceService = performanceService;
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
    public ResponseEntity<?> addNewEvent(@RequestBody CreateEventDto dto){
        try {
            User user = userService.loadUserById(dto.getUserId());
            Stage stage = stageService.getByStageId(dto.getStageId());
//            converting from dto
            Event newEvent = EventMapper.toEntity(dto, stage, user);
            Event savedEvent = eventService.saveNewEvent(newEvent);
//            adding performers to event
            List<Performer> performersOnEvent = performerService.getAllPerformersByIds(Arrays.asList(dto.getPerformersIds()));
            performanceService.savePerformancesForEvent(performersOnEvent, newEvent);
            return ResponseEntity.ok(savedEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id,
                             @RequestBody CreateEventDto dto){
        try {
            User user = userService.loadUserById(dto.getUserId());
            Stage stage = stageService.getByStageId(dto.getStageId());
//            converting from dto
            Event newEvent = EventMapper.toEntity(dto, stage, user);
            Event updatedEvent = eventService.updateEvent(id, newEvent);
            //            adding performers to event
//            performers can be empty list
            List<Performer> performersOnEvent = performerService.getAllPerformersByIds(Arrays.asList(dto.getPerformersIds()));
            performanceService.updatePerformancesForEvent(performersOnEvent, updatedEvent);
            return ResponseEntity.ok(updatedEvent);
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
