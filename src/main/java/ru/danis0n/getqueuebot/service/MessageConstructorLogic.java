package ru.danis0n.getqueuebot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.danis0n.getqueuebot.dao.UserDAO;
import ru.danis0n.getqueuebot.model.entites.User;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageConstructorLogic {

    final UserDAO userDAO;

    public MessageConstructorLogic(UserDAO userDAO) {
        this.userDAO = userDAO;
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
}
