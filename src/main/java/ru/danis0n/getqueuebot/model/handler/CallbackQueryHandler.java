package ru.danis0n.getqueuebot.model.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CallbackQueryHandler {

    @Autowired
    public CallbackQueryHandler(){

    }

    public SendMessage processCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }

}
