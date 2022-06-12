package ru.danis0n.getqueuebot.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.danis0n.getqueuebot.model.context.BotStateContext;
import ru.danis0n.getqueuebot.model.handler.CallbackQueryHandler;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotFacade {

    final BotStateContext botStateContext;
    final CallbackQueryHandler callbackQueryHandler;

    public SendMessage handleUpdate(Update update) {
        if(update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else{
            Message message = update.getMessage();
            if(message != null && message.hasText()){
                return handleInputMessage(message);
            }
        }
        return null;
    }

    private SendMessage handleInputMessage(Message message) {
        return botStateContext.executeContext(message);
    }
}
