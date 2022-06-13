package ru.danis0n.getqueuebot.model.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.danis0n.getqueuebot.model.manager.CallbackQueryManager;

@Component
public class CallbackQueryHandler {

    final CallbackQueryManager callbackQueryManager;

    @Autowired
    public CallbackQueryHandler(CallbackQueryManager callbackQueryManager){
        this.callbackQueryManager = callbackQueryManager;
    }

    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }

}
