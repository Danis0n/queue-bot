package ru.danis0n.getqueuebot.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetQueueBot extends SpringWebhookBot {

    String botPath;
    String botUsername;
    String botToken;
    BotFacade botFacade;

    public GetQueueBot(BotFacade botFacade, DefaultBotOptions botOptions, SetWebhook setWebhook) {
        super(botOptions,setWebhook);
        this.botFacade = botFacade;
    }

    public GetQueueBot(BotFacade botFacade, SetWebhook setWebhook) {
        super(setWebhook);
        this.botFacade = botFacade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return botFacade.handleUpdate(update);
    }

}
