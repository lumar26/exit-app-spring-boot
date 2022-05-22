package rs.bg.ac.fon.elab.iteh.exit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.bg.ac.fon.elab.iteh.exit.model.Stage;
import rs.bg.ac.fon.elab.iteh.exit.model.User;
import rs.bg.ac.fon.elab.iteh.exit.repository.StageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    private final StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public Stage getByStageId(Long id) throws Exception {
        Optional<Stage> optionalStage = stageRepository.findById(id);
        if (optionalStage.isEmpty()) throw new Exception("Stage with id = " + id + "does not exist");
        return optionalStage.get();
    }

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Stage addNewStage(Stage newStage) {
//        ovo smemo da uradimo jer je jwt token vec proveren od strane Spring Security
        newStage.getUser().setRole(User.Role.ROLE_ADMIN);
        return stageRepository.save(newStage);
    }

    public Stage updateStage(Long id, Stage newStage) throws Exception {
        Optional<Stage> optionalStage = stageRepository.findById(id);
        if (optionalStage.isEmpty())
            throw new Exception("Cannot update stage. Stage with id = " + id + "does not exist");
        newStage.setId(id);
//        ovo smemo da uradimo jer je jwt token vec proveren od strane Spring Security
        newStage.getUser().setRole(User.Role.ROLE_ADMIN);
        return stageRepository.save(newStage); // todo: check if this is possible
    }

    public Stage deleteStageById(Long id) throws Exception {
        Optional<Stage> optionalStage = stageRepository.findById(id);
        if (optionalStage.isEmpty())
            throw new Exception("Cannot delete stage. Stage with id = " + id + "does not exist");
        stageRepository.deleteById(id);
        return optionalStage.get();
    }
}
