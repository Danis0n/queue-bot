package ru.danis0n.getqueuebot.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.danis0n.getqueuebot.model.handler.CallbackQueryHandler;
import ru.danis0n.getqueuebot.model.handler.MessageHandler;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotFacade {

    final CallbackQueryHandler callbackQueryHandler;
    final MessageHandler messageHandler;

    public SendMessage handleUpdate(Update update) {
        if(update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.handleCallbackQuery(callbackQuery);
        } else{
            Message message = update.getMessage();
            if(message != null && message.hasText()){
                return messageHandler.handleInputMessage(message);
            }
        }
        return null;
    }
}
