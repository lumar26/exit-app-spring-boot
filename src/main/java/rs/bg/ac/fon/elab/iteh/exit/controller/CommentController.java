package rs.bg.ac.fon.elab.iteh.exit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.bg.ac.fon.elab.iteh.exit.dto.CommentSaveDto;
import rs.bg.ac.fon.elab.iteh.exit.model.Comment;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.service.CommentService;
import rs.bg.ac.fon.elab.iteh.exit.service.EventService;
import rs.bg.ac.fon.elab.iteh.exit.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final EventService eventService;

    public CommentController(CommentService commentService, UserService userService, EventService eventService) {
        this.commentService = commentService;
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/event/{event}")
    public ResponseEntity<?> getAllCommentsForEvent(@PathVariable("event") Long eventId){
        try {
            return ResponseEntity.ok(commentService.getAllCommentsByEventId(eventId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveNewComment(@RequestBody CommentSaveDto dto){
        try {
            User user = userService.loadUserById(dto.getUserId());
            Event event = eventService.getEventById(dto.getEventId());
            Comment comment = new Comment(0L, dto.getRate(), dto.getContent(), event, user);
            return ResponseEntity.ok(commentService.saveNewComment(comment));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Could not save comment. " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(commentService.deleteCommentById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
