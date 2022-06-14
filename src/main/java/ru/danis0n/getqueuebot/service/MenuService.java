package ru.danis0n.getqueuebot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.danis0n.getqueuebot.dao.LessonDAO;
import ru.danis0n.getqueuebot.model.entites.Lesson;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuService {

    @Value("${telegrambot.adminId}")
    int adminId;

    final LessonDAO lessonDAO;

    public MenuService(LessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }


    public boolean isAdmin(long userId){
        return adminId == userId;
    }

    private SendMessage createMessageWithKeyboard(final long chatId, String textMessage,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup)
    {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textMessage);
        if(replyKeyboardMarkup != null){
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            return sendMessage;
        }
        return sendMessage;
    }

    public SendMessage getMainMenuMessage(final long chatId, String textMessage, final long userId){
        final ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard(userId);
        return createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
    }

    private ReplyKeyboardMarkup getMainMenuKeyboard(long userId){
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        if(isAdmin(userId)){
            KeyboardRow adminPanel = new KeyboardRow();
            adminPanel.add(new KeyboardButton("Все пользователи"));
            keyboard.add(adminPanel);
        }

        KeyboardRow showLessons = new KeyboardRow();
        showLessons.add(new KeyboardButton("Доступные предметы"));
        keyboard.add(showLessons);

        KeyboardRow aboutPanel = new KeyboardRow();
        aboutPanel.add(new KeyboardButton("Об авторе"));
        aboutPanel.add(new KeyboardButton("Помощь"));
        keyboard.add(aboutPanel);


        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboard getInlineLessons(){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<Lesson> lessons = lessonDAO.findAll();
        List<List<InlineKeyboardButton>> row = new ArrayList<>();

        for(Lesson l : lessons){
            InlineKeyboardButton button = new InlineKeyboardButton();
            List<InlineKeyboardButton> inlineButtons = new ArrayList<>();
            button.setText(l.getName());
            button.setCallbackData(l.getStdId());
            inlineButtons.add(button);
            row.add(inlineButtons);
        }
        inlineKeyboardMarkup.setKeyboard(row);
        return inlineKeyboardMarkup;
    }

    public ReplyKeyboard getInlineLessonMenu() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> row = new ArrayList<>();

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();

        InlineKeyboardButton queueButton = new InlineKeyboardButton();
        queueButton.setCallbackData("queue");
        queueButton.setText("Текущая очередь");
        inlineKeyboardButtons.add(queueButton);

        InlineKeyboardButton bookButton = new InlineKeyboardButton();
        bookButton.setCallbackData("tobook");
        bookButton.setText("Записаться");
        inlineKeyboardButtons.add(bookButton);

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setCallbackData("back");
        backButton.setText("Назад");
        inlineKeyboardButtons.add(backButton);

        row.add(inlineKeyboardButtons);

        inlineKeyboardMarkup.setKeyboard(row);
        return inlineKeyboardMarkup;
    }
}
