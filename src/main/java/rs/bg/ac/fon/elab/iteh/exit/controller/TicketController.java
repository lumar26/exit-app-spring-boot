package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.model.Ticket;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TicketController {
    @GetMapping("{id}")
    public Ticket getTicketById(@PathVariable Long id){
        return null;
    }

    @GetMapping
    public List<Ticket> getAllTicket(){
        return List.of();
    }

    @PostMapping
    public Ticket addNewTicket(@RequestBody Ticket newTicket){
        return null;
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id,
                             @RequestBody Ticket newTicket){
        return null;
    }

    @DeleteMapping("/{id}")
    public Ticket deleteTicket(@PathVariable Long id){
        return null;
    }
}
