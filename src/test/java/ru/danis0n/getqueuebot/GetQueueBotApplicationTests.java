package ru.danis0n.getqueuebot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danis0n.getqueuebot.dao.StateDAO;
import ru.danis0n.getqueuebot.dao.UserDAO;
import ru.danis0n.getqueuebot.model.BotState;
import ru.danis0n.getqueuebot.model.entites.State;
import ru.danis0n.getqueuebot.model.entites.User;
import ru.danis0n.getqueuebot.repo.BotStateRepository;
import ru.danis0n.getqueuebot.repo.UserRepository;

@SpringBootTest
class GetQueueBotApplicationTests {

    @Autowired
    UserDAO userDAO;
    @Autowired
    StateDAO stateDAO;

    @Autowired
    BotStateRepository botStateRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    void saveUser(){
        State state = stateDAO.getById(3);
        User user = new User();
        user.setName("Danis0n");
        user.setId(93219);
        user.setState(state);
        userDAO.save(user);
    }

    @Test
    void saveState(){
        State state = new State();
        state.setBotState(BotState.SHOWUSERS.name());
        stateDAO.save(state);
    }

    @Test
    void deleteState(){
        stateDAO.deleteById(1);
        stateDAO.deleteById(2);
    }

    @Test
    void deleteUser(){
        userDAO.deleteById(471489235);
    }

    @Test
    void getStateId(){
        State state = botStateRepository.findById(1);
        System.out.println(state.toString());
    }

    @Test
    void testEnum(){
        BotState botState = userDAO.findByUserId(93219).getState().getBotStateEnum(userDAO.findByUserId(93219).getState().getBotState());
        System.out.println(botState);
    }

}
