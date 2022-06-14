package ru.danis0n.getqueuebot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.danis0n.getqueuebot.dao.LessonDAO;
import ru.danis0n.getqueuebot.model.entites.Lesson;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageConstructor {

    final MessageConstructorLogic messageLogic;
    final LessonDAO lessonDAO;
    final MenuService menuService;

    public MessageConstructor(MessageConstructorLogic messageLogic, LessonDAO lessonDAO, MenuService menuService) {
        this.messageLogic = messageLogic;
        this.lessonDAO = lessonDAO;
        this.menuService = menuService;
    }
    // TODO : implement text file for messages

    public BotApiMethod<?> helpMenu(Message message, long userId){
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

    public BotApiMethod<?> startMenu(Message message, long userId) {
        return menuService.getMainMenuMessage(message.getChatId(),"Добро пожаловать к боту на огонёк",userId);
    }

    public BotApiMethod<?> allLessonMenu(Message message, long userId) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setChatId(String.valueOf(userId));
        replyMessage.setText("Список предметов");
        replyMessage.setReplyMarkup(menuService.getInlineLessons());
        return replyMessage;
    }

    public BotApiMethod<?> printCurrentLesson(Lesson lesson, long userId) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setChatId(String.valueOf(userId));
        replyMessage.setText(messageLogic.buildLesson(lesson).toString());
        replyMessage.setReplyMarkup(menuService.getInlineLessonMenu());
        return replyMessage;
    }

    public BotApiMethod<?> getActualQueue(Lesson lesson, long userId) {
        return null;
    }

    public BotApiMethod<?> bookToQueue(Lesson lesson, long userId) {
        return null;
    }

    public BotApiMethod<?> backToLessons(CallbackQuery callbackQuery, long userId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setMessageId(callbackQuery.getMessage().getMessageId());
        deleteMessage.setChatId(String.valueOf(userId));
        return deleteMessage;
    }
}
