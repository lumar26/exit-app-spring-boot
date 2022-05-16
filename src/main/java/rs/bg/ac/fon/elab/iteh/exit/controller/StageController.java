package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;
import rs.bg.ac.fon.elab.iteh.exit.service.StageService;

import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    public final StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStageById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stageService.getByStageId(id));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Stage>> getAllStages() {
        return ResponseEntity.ok(stageService.getAllStages());
    }

    @PostMapping("/add")
    public Stage addNewStage(@RequestBody Stage newStage) {
        return stageService.addNewStage(newStage);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStage(@PathVariable Long id,
                                         @RequestBody Stage newStage) {
        try {
            return ResponseEntity.ok(stageService.updateStage(id, newStage));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStage(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stageService.deleteStageById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
