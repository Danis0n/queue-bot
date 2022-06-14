package ru.danis0n.getqueuebot.model.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.danis0n.getqueuebot.model.manager.CallbackQueryManager;
import ru.danis0n.getqueuebot.model.manager.CallbackQueryStateManager;

@Component
public class CallbackQueryHandler {

    final CallbackQueryStateManager callbackQueryStateManager;

    @Autowired
    public CallbackQueryHandler(CallbackQueryStateManager callbackQueryStateManager){
        this.callbackQueryStateManager = callbackQueryStateManager;
    }

    public BotApiMethod<?> handleCallbackQuery(CallbackQuery callbackQuery) {
        return callbackQueryStateManager.executeContext(callbackQuery);
    }

}
