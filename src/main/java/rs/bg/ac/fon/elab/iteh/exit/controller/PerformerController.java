package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;
import rs.bg.ac.fon.elab.iteh.exit.service.PerformerService;

import java.util.List;

@RestController
@RequestMapping("/api/performers")
public class PerformerController {

    private final PerformerService performerService;

    @Autowired
    public PerformerController(PerformerService performerService) {
        this.performerService = performerService;
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
    public List<Performer> getAllPerformers(){
        return performerService.getAllPerformers();
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
