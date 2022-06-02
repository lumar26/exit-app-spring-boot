package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.dto.TicketSaveDto;
import rs.bg.ac.fon.elab.iteh.exit.model.Ticket;
import rs.bg.ac.fon.elab.iteh.exit.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TicketController {

    private final TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    public List<Ticket> getAllTicket() {
        return service.getAllTickets();
    }

    @GetMapping("/user/{user}")
    public List<Ticket> getAllTicketsOfUser(@PathVariable("user") Long userId) {
        return service.getAllTicketsByOwnerId(userId);
    }

    @PostMapping
    public ResponseEntity<?> saveTickets(@RequestBody TicketSaveDto[] dto) {


        try {
            List<Ticket> result = service.saveNewTickets(dto);
            return ResponseEntity.ok(
                    result
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Could not save new ticket. \n" + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id,
                               @RequestBody Ticket newTicket) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Ticket deleteTicket(@PathVariable Long id) {
        return null;
    }
}
