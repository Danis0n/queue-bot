package ru.danis0n.getqueuebot.model.manager;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.danis0n.getqueuebot.model.entites.Lesson;
import ru.danis0n.getqueuebot.service.MessageConstructor;

public interface CallBackHandler {
    BotApiMethod<?> execute(Lesson lesson, MessageConstructor messageConstructor, CallbackQuery callbackQuery, long userId);
}
