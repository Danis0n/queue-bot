package ru.danis0n.getqueuebot.service;

import lombok.Setter;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.danis0n.getqueuebot.config.ApplicationContextProvider;
import ru.danis0n.getqueuebot.model.GetQueueBot;

@Setter
//thread with event
public class SendEvent extends Thread {

    private SendMessage sendMessage;

    public SendEvent() {
    }

    @SneakyThrows
    @Override
    public void run() {
        GetQueueBot telegramBot = ApplicationContextProvider.getApplicationContext().getBean(GetQueueBot.class);
        telegramBot.execute(sendMessage);
    }
}
