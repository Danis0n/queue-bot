package ru.danis0n.getqueuebot.model.context;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Handler {
    SendMessage execute(Message message, long userId);
}
