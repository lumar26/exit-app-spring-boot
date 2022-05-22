package rs.bg.ac.fon.elab.iteh.exit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.bg.ac.fon.elab.iteh.exit.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByEvent_Id(Long eventId);
}
