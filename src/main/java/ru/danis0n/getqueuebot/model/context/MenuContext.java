package ru.danis0n.getqueuebot.model.context;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.danis0n.getqueuebot.model.BotState;
import ru.danis0n.getqueuebot.model.handler.Handler;

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
        return null;
    }
}

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuContext {

    final Map<BotState, Handler> menuStrategy = new HashMap<>();

    public MenuContext(){
        menuStrategy.put(BotState.START,new AboutMenu());
        menuStrategy.put(BotState.SHOWUSERS,new AllUsersMenu());
        menuStrategy.put(BotState.HELP,new HelpMenu());
    }

    public SendMessage executeContext(BotState searchState,Message message, long usedId){
        return menuStrategy.get(searchState).execute(message,usedId);
    }
}
