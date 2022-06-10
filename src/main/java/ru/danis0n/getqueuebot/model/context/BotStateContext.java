package ru.danis0n.getqueuebot.model.context;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.danis0n.getqueuebot.model.BotState;

import java.util.HashMap;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotStateContext {

    final Map<String, BotState> stateStrategy = new HashMap<>();
    final MenuContext menuContext;

    public BotStateContext(MenuContext menuContext){
        this.menuContext = menuContext;
        stateStrategy.put("/start", BotState.START);
        stateStrategy.put("Все пользователи", BotState.SHOWUSERS);
        stateStrategy.put("Помощь", BotState.HELP);
    }

    public SendMessage executeContext(Message message){
        String inputMsg = message.getText();
        BotState botState = stateStrategy.get(inputMsg);
        long userId = message.getFrom().getId();
        return menuContext.executeContext(botState,message,userId);
    }
}
