package ru.danis0n.getqueuebot.model.handler;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.danis0n.getqueuebot.model.manager.StateManager;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageHandler {

    final StateManager stateManager;

    @Autowired
    public MessageHandler(StateManager stateManager){
        this.stateManager = stateManager;
    }

    public SendMessage handleInputMessage(Message message) {
        return stateManager.executeContext(message);
    }
}
