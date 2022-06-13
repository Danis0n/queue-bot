package ru.danis0n.getqueuebot.model.manager;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.danis0n.getqueuebot.dao.StateDAO;
import ru.danis0n.getqueuebot.dao.UserDAO;
import ru.danis0n.getqueuebot.model.BotState;
import ru.danis0n.getqueuebot.service.MessageConstructor;

import java.util.HashMap;
import java.util.Map;

class AboutMenu implements Handler{

    @Override
    public SendMessage execute(Message message, long userId, MessageConstructor messageConstructor) {
        return messageConstructor.aboutMenu(message,userId);
    }
}

class AllUsersMenu implements Handler{

    @Override
    public SendMessage execute(Message message, long userId, MessageConstructor messageConstructor) {
        return messageConstructor.allUsersMenu(message,userId);
    }
}

class HelpMenu implements Handler{

    @Override
    public SendMessage execute(Message message, long userId, MessageConstructor messageConstructor) {
       return messageConstructor.helpMenu(message,userId);
    }
}

class StartMenu implements Handler{

    @Override
    public SendMessage execute(Message message, long userId, MessageConstructor messageConstructor) {
        return messageConstructor.startMenu(message,userId);
    }
}

@Getter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuManager {

    final UserDAO userDAO;
    final StateDAO stateDAO;
    final MessageConstructor messageConstructor;
    final Map<BotState, Handler> menuStrategy = new HashMap<>();

    @Autowired
    public MenuManager(UserDAO userDAO, StateDAO stateDAO, MessageConstructor messageConstructor){
        this.userDAO = userDAO;
        this.stateDAO = stateDAO;
        this.messageConstructor = messageConstructor;
        menuStrategy.put(BotState.START,new StartMenu());
        menuStrategy.put(BotState.SHOWUSERS,new AllUsersMenu());
        menuStrategy.put(BotState.HELP,new HelpMenu());
    }

    public SendMessage executeContext(BotState searchState,Message message, long userId){
        return menuStrategy.get(searchState).execute(message,userId, messageConstructor);
    }
}