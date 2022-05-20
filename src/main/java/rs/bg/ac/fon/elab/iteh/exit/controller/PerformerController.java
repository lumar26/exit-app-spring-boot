package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Performance;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;
import rs.bg.ac.fon.elab.iteh.exit.service.PerformanceService;
import rs.bg.ac.fon.elab.iteh.exit.service.PerformerService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/performers")
@CrossOrigin(origins = "*",
        methods = {GET, POST, PUT, DELETE, OPTIONS})
public class PerformerController {

    private final PerformerService performerService;
    private final PerformanceService performanceService;

    @Autowired
    public PerformerController(PerformerService performerService, PerformanceService performanceService) {
        this.performerService = performerService;
        this.performanceService = performanceService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPerformerById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(performerService.getByPerformerId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Performer>> getAllPerformers(){
        return ResponseEntity.ok(performerService.getAllPerformers());
    }

    @GetMapping("/event/{event}")
    public ResponseEntity<List<Performer>> getAllPerformersOfEvent(@PathVariable("event") Long eventId){
        List<Performance> performances = performanceService.getAllPerformancesOfEvent(eventId);
        List<Performer> result = performances.stream().map(Performance::getPerformer).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public Performer addNewPerformer(@RequestBody Performer newPerformer){
        return performerService.saveNewPerformer(newPerformer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerformer(@PathVariable Long id,
                             @RequestBody Performer newPerformer){
        try {
            return ResponseEntity.ok(performerService.updatePerformer(id, newPerformer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerformer(@PathVariable Long id){
        try {
            return ResponseEntity.ok(performerService.deletePerformerById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
