package ru.danis0n.getqueuebot.model.context;


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
import ru.danis0n.getqueuebot.model.entites.User;

import java.util.HashMap;
import java.util.Map;

class AboutMenu implements Handler{

    @Override
    public SendMessage execute(Message message, long userId) {
        return null;
    }
}

class AllUsersMenu implements Handler{

    @Override
    public SendMessage execute(Message message, long userId) {
        return null;
    }
}

class HelpMenu implements Handler{

    @Override
    public SendMessage execute(Message message, long userId) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setChatId(String.valueOf(userId));
        replyMessage.setText("В разработке..");
        return replyMessage;
    }
}

@Getter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuContext {

    final Map<BotState, Handler> menuStrategy = new HashMap<>();
    final UserDAO userDAO;
    final StateDAO stateDAO;

    @Autowired
    public MenuContext(UserDAO userDAO, StateDAO stateDAO){
        this.userDAO = userDAO;
        this.stateDAO = stateDAO;
        menuStrategy.put(BotState.START,new AboutMenu());
        menuStrategy.put(BotState.SHOWUSERS,new AllUsersMenu());
        menuStrategy.put(BotState.HELP,new HelpMenu());
    }


    public SendMessage executeContext(BotState searchState,Message message, long userId){

        User user = new User();
        user.setName(message.getFrom().getUserName());
        user.setId(userId);
        user.setState(stateDAO.getById(searchState.getId()));
        userDAO.save(user);
        return menuStrategy.get(searchState).execute(message,userId);
    }
}
