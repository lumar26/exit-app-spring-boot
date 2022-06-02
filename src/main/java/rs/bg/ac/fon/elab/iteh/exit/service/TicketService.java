package rs.bg.ac.fon.elab.iteh.exit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.dto.TicketSaveDto;
import rs.bg.ac.fon.elab.iteh.exit.mapper.TicketMapper;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;
import rs.bg.ac.fon.elab.iteh.exit.model.Ticket;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.repository.StageRepository;
import rs.bg.ac.fon.elab.iteh.exit.repository.TicketRepository;
import rs.bg.ac.fon.elab.iteh.exit.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final StageRepository stageRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, StageRepository stageRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.stageRepository = stageRepository;
    }


    public List<Ticket> getAllTicketsByOwnerId(Long userId) {
        return ticketRepository.findAllByOwner_Id(userId);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket saveNewTicket(Ticket newTicket) {
        return ticketRepository.save(newTicket);
    }

    public List<Ticket> saveNewTickets(TicketSaveDto[] dto) throws Exception {
        List<Ticket> toBeSaved = new ArrayList<>();
        for (TicketSaveDto ticketDto: dto){
            Optional<User> optionalOwner  = userRepository.findById(ticketDto.getOwnerId());
            Optional<Stage> optionalStage = stageRepository.findById(ticketDto.getStageId());
            if (optionalOwner.isEmpty())
                throw new Exception("Cannot save tickets, owner with id = " +  ticketDto.getOwnerId() + " does not exist");
            if (!optionalOwner.get().getRole().equals(User.Role.ROLE_USER))
                throw new Exception("Cannot save tickets, owner must have role USER");
            if (optionalStage.isEmpty())
                throw new Exception("Cannot save tickets, stage with id = " +  ticketDto.getStageId() + " does not exist");
            toBeSaved.add(TicketMapper.toEntity(ticketDto, optionalOwner.get(), optionalStage.get()));
        }
        return ticketRepository.saveAll(toBeSaved);
    }
}
