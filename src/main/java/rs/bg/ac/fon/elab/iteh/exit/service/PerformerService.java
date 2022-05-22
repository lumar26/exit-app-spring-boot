package rs.bg.ac.fon.elab.iteh.exit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.model.Performer;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.repository.PerformerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PerformerService {

    private final PerformerRepository repository;

    @Autowired
    public PerformerService(PerformerRepository repository) {
        this.repository = repository;
    }

    public Performer getByPerformerId(Long id) throws Exception {
        Optional<Performer> optionalPerformer = repository.findById(id);
        if (optionalPerformer.isEmpty()) throw new Exception("Performer with id = " + id + "does not exist");
        return optionalPerformer.get();
    }

    public List<Performer> getAllPerformers() {
        return repository.findAll();
    }

    public List<Performer> getAllPerformersByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Transactional
    public Performer saveNewPerformer(Performer newPerformer) {
//        moguce nakon uspesne autentifikacije
        newPerformer.getUser().setRole(User.Role.ROLE_ADMIN);
        return repository.save(newPerformer);
    }

    @Transactional
    public Performer updatePerformer(Long id, Performer newPerformer) throws Exception {
        Optional<Performer> optionalStage = repository.findById(id);
        if (optionalStage.isEmpty())
            throw new Exception("Cannot update performer. Performer with id = " + id + "does not exist");
        newPerformer.setId(id);
//        ovo smemo da uradimo jer je jwt token vec proveren od strane Spring Security
        newPerformer.getUser().setRole(User.Role.ROLE_ADMIN);
        return repository.save(newPerformer);
    }

    @Transactional
    public Performer deletePerformerById(Long id) throws Exception {
        Optional<Performer> optionalStage = repository.findById(id);
        if (optionalStage.isEmpty())
            throw new Exception("Cannot delete performer. Performer with id = " + id + "does not exist");
        repository.deleteById(id);
        return optionalStage.get();
    }
}
