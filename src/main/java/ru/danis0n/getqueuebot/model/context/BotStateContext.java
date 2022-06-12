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
import ru.danis0n.getqueuebot.model.entites.State;
import ru.danis0n.getqueuebot.model.entites.User;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotStateContext {

    final UserDAO userDAO;
    final StateDAO stateDAO;
    final MenuContext menuContext;
    final Map<String, BotState> stateStrategy = new HashMap<>();

    @Autowired
    public BotStateContext(UserDAO userDAO, StateDAO stateDAO, MenuContext menuContext){
        this.userDAO = userDAO;
        this.stateDAO = stateDAO;
        this.menuContext = menuContext;
        stateStrategy.put("/start", BotState.START);
        stateStrategy.put("Помощь", BotState.HELP);
        stateStrategy.put("Все пользователи", BotState.SHOWUSERS);
    }

    public SendMessage saveNewUser(Message message,long userId){
        SendMessage sendMessage = new SendMessage();
        User user = new User();
        user.setId(userId);
        user.setName(message.getFrom().getUserName());
        user.setState(stateDAO.getById(1));
        userDAO.save(user);
        sendMessage.setChatId(String.valueOf(userId));
        sendMessage.setText("Добро пожаловать!");
        return sendMessage;
    }

    public SendMessage executeContext(Message message){
        long userId = message.getFrom().getId();
        String inputMsg = message.getText();

        if(!userDAO.isExistById(userId)){
            return saveNewUser(message,userId);
        }

        State state = userDAO.findByUserId(userId).getState();
        String tmpState = state.getBotState();

        BotState botState = stateStrategy.get(inputMsg) == null ?
                state.getBotStateEnum(tmpState): stateStrategy.get(inputMsg);
        return menuContext.executeContext(botState,message,userId);
    }
}
