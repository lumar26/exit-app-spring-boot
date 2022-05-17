package rs.bg.ac.fon.elab.iteh.exit.mapper;

import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.dto.CreateEventDto;
import rs.bg.ac.fon.elab.iteh.exit.model.Event;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;
import rs.bg.ac.fon.elab.iteh.exit.model.User;

@Service
public class EventMapper {
    public static Event toEntity(CreateEventDto dto){
        return new Event(dto.getName(), dto.getStart(), dto.getImage());
    }

    public static Event toEntity(CreateEventDto dto, Stage stage, User user){
        Event result = new Event(dto.getName(), dto.getStart(), dto.getImage());
        result.setStage(stage);
        result.setUser(user);
        return result;
    }
}
