package ru.danis0n.getqueuebot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.danis0n.getqueuebot.botconfig.BotConfig;
import ru.danis0n.getqueuebot.model.GetQueueBot;
import ru.danis0n.getqueuebot.model.BotFacade;

@Configuration
@AllArgsConstructor
public class AppConfig {
    private final BotConfig botConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
    }

    @Bean
    public GetQueueBot springWebhookBot(SetWebhook setWebhook, BotFacade botFacade) {
        GetQueueBot bot = new GetQueueBot(botFacade, setWebhook);
        bot.setBotToken(botConfig.getBotToken());
        bot.setBotUsername(botConfig.getUserName());
        bot.setBotPath(botConfig.getWebHookPath());
        return bot;
    }

}
