package ru.danis0n.getqueuebot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.danis0n.getqueuebot.dao.LessonDAO;
import ru.danis0n.getqueuebot.dao.UserDAO;
import ru.danis0n.getqueuebot.model.entites.Lesson;
import ru.danis0n.getqueuebot.model.entites.User;

import java.util.List;
import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageConstructorLogic {

    final UserDAO userDAO;
    final LessonDAO lessonDAO;

    public MessageConstructorLogic(UserDAO userDAO, LessonDAO lessonDAO) {
        this.userDAO = userDAO;
        this.lessonDAO = lessonDAO;
    }

    public StringBuilder buildAllUsers() {
        StringBuilder builder = new StringBuilder();
        List<User> users = userDAO.findAllUsers();
        int counter = 1;
        for(User u : users){
            builder.append(counter++).append(". ").
                    append(u.getName()).append(". ").
                    append("State ").append(u.getState().getBotState()).
                    append(".").append("\n");
        }
        return builder;
    }

    public StringBuilder buildLesson(Lesson lesson) {
        StringBuilder stringBuilder = new StringBuilder();
        // TODO : add new params for lesson
        stringBuilder.append(lesson.getName()).append("\n");
        return stringBuilder;
    }
}
