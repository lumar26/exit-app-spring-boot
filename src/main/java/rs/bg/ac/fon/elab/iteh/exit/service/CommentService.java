package rs.bg.ac.fon.elab.iteh.exit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.model.Comment;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.repository.CommentRepository;
import rs.bg.ac.fon.elab.iteh.exit.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository repository;
    private final EventRepository eventRepository;

    @Autowired
    public CommentService(CommentRepository repository, EventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public List<Comment> getAllCommentsByEventId(Long eventId) throws Exception {
        if (eventId < 1) throw new Exception("Could not retrieve comments for events. Invalid event id passed.");
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty())
            throw new Exception("Could not retrieve comments for events. Event with given id = " + eventId + "does not exist.");
        return repository.getAllByEvent_Id(eventId);
    }

    public Comment saveNewComment(Comment comment) {
        return repository.save(comment);
    }

    public Comment deleteCommentById(Long id) throws Exception {
        Optional<Comment> optionalComment = repository.findById(id);
        if (optionalComment.isEmpty()) throw new Exception("Could not delete comment. Comment with id = " + id + " does not exist");
        repository.deleteById(id);
        return optionalComment.get();
    }

    public List<Comment> getAllComments() {
        return repository.findAll();
    }
}
