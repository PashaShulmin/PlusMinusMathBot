package ru.thirdcourse.courseproject.Shulmin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.thirdcourse.courseproject.Shulmin.telegram.MyMathTelegramBot;
import ru.thirdcourse.courseproject.Shulmin.telegram.handlers.MessageHandler;

@Configuration
@PropertySource("classpath:application.properties")
public class BotConfig {
    @Value("${telegram.bot-name}")
    private String botName;
    @Value("${telegram.bot-token}")
    private String botToken;
    @Value("${telegram.webhook-path}")
    private String webHookPath;

    public String getBotName() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }

    public String getWebHook() {
        return webHookPath;
    }

    @Bean
    public MyMathTelegramBot myMathTelegramBot(MessageHandler messageHandler) {
        SetWebhook setWebhook = SetWebhook.builder().url(webHookPath).build();

        return new MyMathTelegramBot(setWebhook, botToken, botName, messageHandler);
    }
}
