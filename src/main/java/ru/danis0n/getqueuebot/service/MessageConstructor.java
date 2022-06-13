package ru.danis0n.getqueuebot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageConstructor {

    final MessageConstructorLogic messageLogic;
    final MenuService menuService;

    public MessageConstructor(MessageConstructorLogic messageLogic, MenuService menuService) {
        this.messageLogic = messageLogic;
        this.menuService = menuService;
    }
    // TODO : implement text file for messages

    public SendMessage helpMenu(Message message, long userId){
        SendMessage replyMessage = new SendMessage();
        replyMessage.setChatId(String.valueOf(userId));
        replyMessage.setText("Помощь : В разработке..");
        return replyMessage;
    }

    public SendMessage aboutMenu(Message message, long userId){
        SendMessage replyMessage = new SendMessage();
        replyMessage.setChatId(String.valueOf(userId));
        replyMessage.setText("Об авторе : в разработке..");
        return replyMessage;
    }

    public SendMessage allUsersMenu(Message message, long userId){
        SendMessage replyMessage = new SendMessage();
        replyMessage.setChatId(String.valueOf(userId));
        replyMessage.setText(messageLogic.buildAllUsers().toString());
        return replyMessage;
    }

    public SendMessage startMenu(Message message, long userId) {
        return null;
    }
}
