package ru.danis0n.getqueuebot.model.manager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.danis0n.getqueuebot.service.MessageConstructor;

public interface Handler {
    SendMessage execute(Message message, long userId, MessageConstructor messageConstructor);
}
