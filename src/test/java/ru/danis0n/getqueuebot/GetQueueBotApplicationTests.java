package ru.danis0n.getqueuebot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danis0n.getqueuebot.dao.StateDAO;
import ru.danis0n.getqueuebot.dao.UserDAO;
import ru.danis0n.getqueuebot.model.BotState;
import ru.danis0n.getqueuebot.model.entites.Lesson;
import ru.danis0n.getqueuebot.model.entites.State;
import ru.danis0n.getqueuebot.model.entites.User;
import ru.danis0n.getqueuebot.model.entites.UserLLog;
import ru.danis0n.getqueuebot.repo.BotStateRepository;
import ru.danis0n.getqueuebot.repo.LessonRepository;
import ru.danis0n.getqueuebot.repo.UserLLogsRepository;
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

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    UserLLogsRepository userLLogsRepository;

    @Test
    void saveLLogs(){
        Lesson lesson = lessonRepository.getById(1);

        System.out.println(lesson);
        String data = "oop";
        UserLLog userLLog = new UserLLog();
        userLLog.setId(471489235L);
        userLLog.setData(data);
        userLLog.setLesson(lesson);
        userLLogsRepository.save(userLLog);
    }

    @Test
    void deleteLLogs(){
        userLLogsRepository.deleteById(471489235L);
    }


    @Test
    void saveLesson(){
        Lesson lesson = new Lesson();
        lesson.setName("ООП");
        lesson.setStdId("oop");
        lessonRepository.save(lesson);
    }

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
        state.setBotState(BotState.BACK.getName());
        stateDAO.save(state);
    }

    @Test
    void deleteState(){
        stateDAO.deleteById(10);
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

}
