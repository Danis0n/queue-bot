package ru.danis0n.getqueuebot.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MenuService {

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

    // TODO : add admin panel check
    private ReplyKeyboardMarkup getMainMenuKeyboard(long userId){
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow adminPanel = new KeyboardRow();
        adminPanel.add(new KeyboardButton("Все пользователи"));
        keyboard.add(adminPanel);

        KeyboardRow aboutPanel = new KeyboardRow();
        aboutPanel.add(new KeyboardButton("Об авторе"));
        aboutPanel.add(new KeyboardButton("Помощь"));
        keyboard.add(aboutPanel);

        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}
