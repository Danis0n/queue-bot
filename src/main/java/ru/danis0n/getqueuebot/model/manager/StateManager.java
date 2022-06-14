package ru.danis0n.getqueuebot.model.manager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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
public class StateManager {

    final UserDAO userDAO;
    final StateDAO stateDAO;
    final MenuManager menuManager;
    final Map<String, BotState> stateStrategy = new HashMap<>();

    @Value("${telegrambot.adminId}")
    int adminId;

    @Autowired
    public StateManager(UserDAO userDAO, StateDAO stateDAO, MenuManager menuManager){
        this.userDAO = userDAO;
        this.stateDAO = stateDAO;
        this.menuManager = menuManager;
        stateStrategy.put("/start", BotState.START);
        stateStrategy.put("Помощь", BotState.HELP);
        stateStrategy.put("Все пользователи", BotState.SHOWUSERS);
        stateStrategy.put("Об авторе", BotState.ABOUT);
        stateStrategy.put("Доступные предметы",BotState.SHOWLESSONS);
    }

    // TODO : improve for better saving..
    public SendMessage saveNewUser(Message message,long userId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(userId));
        User user = new User();
        user.setId(userId);
        user.setName(message.getFrom().getUserName());
        user.setState(stateDAO.getById(1));
        userDAO.save(user);
        sendMessage.setText("Добро пожаловать!");
        return sendMessage;
    }

    public boolean isAdmin(long userId){
        return adminId == userId;
    }

    public BotState getCurrentState(long userId, String inputMsg){
        State state = userDAO.findByUserId(userId).getState();
        String tmpState = state.getBotState();
        return stateStrategy.get(inputMsg) == null ?
                State.getBotStateEnum(tmpState): stateStrategy.get(inputMsg);
    }

    public BotState getUpdateState(long userId, String inputMsg){
        BotState searchState = getCurrentState(userId,inputMsg);

        if(searchState == BotState.SHOWUSERS && !isAdmin(userId)){
            return BotState.START;
        }

        User user = userDAO.findByUserId(userId);
        user.setState(stateDAO.getById(searchState.getId()));
        userDAO.save(user);
        return searchState;
    }

    public BotApiMethod<?> executeContext(Message message){
        long userId = message.getFrom().getId();
        String inputMsg = message.getText();

        if(!userDAO.isExistById(userId)){
            return saveNewUser(message,userId);
        }

        BotState botState = getUpdateState(userId, inputMsg);
        return menuManager.executeContext(botState, message, userId);
    }
}