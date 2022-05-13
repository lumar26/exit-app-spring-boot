package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;

import java.util.List;

@RestController
@RequestMapping("/api/performers")
public class PerformerController {
    @GetMapping("{id}")
    public Performer getPerformerById(@PathVariable Long id){
        return null;
    }

    @GetMapping
    public List<Performer> getAllPerformers(){
        return List.of();
    }

    @PostMapping
    public Performer addNewStage(@RequestBody Performer newPerformer){
        return null;
    }

    @PutMapping("/{id}")
    public Performer updatePerformer(@PathVariable Long id,
                             @RequestBody Performer newPerformer){
        return null;
    }

    @DeleteMapping("/{id}")
    public Performer deletePerformer(@PathVariable Long id){
        return null;
    }
}
