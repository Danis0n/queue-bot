package ru.danis0n.getqueuebot.model.manager;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.danis0n.getqueuebot.dao.StateDAO;
import ru.danis0n.getqueuebot.dao.UserDAO;
import ru.danis0n.getqueuebot.model.BotState;
import ru.danis0n.getqueuebot.model.entites.State;
import ru.danis0n.getqueuebot.model.entites.User;

import java.util.HashMap;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CallbackQueryStateManager {

    @Value("${telegrambot.adminId}")
    int adminId;

    final UserDAO userDAO;
    final StateDAO stateDAO;
    final CallbackQueryManager callbackQueryManager;
    final Map<String, BotState> stateStrategy = new HashMap<>();

    @Autowired
    public CallbackQueryStateManager(UserDAO userDAO, StateDAO stateDAO, CallbackQueryManager callbackQueryManager){
        this.userDAO = userDAO;
        this.stateDAO = stateDAO;
        this.callbackQueryManager = callbackQueryManager;
        stateStrategy.put("net",BotState.LESSON);
        stateStrategy.put("saod",BotState.LESSON);
        stateStrategy.put("unix",BotState.LESSON);
        stateStrategy.put("oop",BotState.LESSON);
        stateStrategy.put("queue",BotState.QUEUE);
        stateStrategy.put("tobook",BotState.TOBOOK);
        stateStrategy.put("back",BotState.BACK);
    }

    public BotState getUpdateState(long userId, String data){
        BotState searchState = stateStrategy.get(data);

        State state = stateDAO.getById(searchState.getId());
        User user = userDAO.getById(userId);
        user.setState(state);
        userDAO.save(user);

        return searchState;
    }

    public BotApiMethod<?> executeContext(CallbackQuery callbackQuery){
        long userId = callbackQuery.getFrom().getId();
        BotState botState = getUpdateState(userId,callbackQuery.getData());

        return callbackQueryManager.executeContext(botState, callbackQuery, userId);
    }

}
