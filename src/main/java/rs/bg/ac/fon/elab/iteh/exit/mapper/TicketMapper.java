package rs.bg.ac.fon.elab.iteh.exit.mapper;

import rs.bg.ac.fon.elab.iteh.exit.dto.TicketSaveDto;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;
import rs.bg.ac.fon.elab.iteh.exit.model.Ticket;
import rs.bg.ac.fon.elab.iteh.exit.model.User;

public class TicketMapper {
    public static Ticket toEntity(TicketSaveDto dto, User user, Stage stage){
        return new Ticket(null, dto.getPrice(), dto.getDescription(), dto.getPurchaseDate(), dto.getDiscount(),
                user, stage);
    }
}
