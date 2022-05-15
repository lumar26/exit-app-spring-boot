package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;

import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {
    @GetMapping("{id}")
    public Stage getStageById(@PathVariable Long id){
        return null;
    }

    @GetMapping
    public List<Stage> getAllStages(){
        return List.of();
    }

    @PostMapping("/add")
    public Stage addNewStage(@RequestBody Stage newStage){
        return null;
    }

    @PutMapping("/update/{id}")
    public Stage updateStage(@PathVariable Long id,
                             @RequestBody Stage newStage){
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public Stage deleteStage(@PathVariable Long id){
        return null;
    }
}
