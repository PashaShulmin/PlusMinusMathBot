package ru.thirdcourse.courseproject.Shulmin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.thirdcourse.courseproject.Shulmin.telegram.MyMathTelegramBot;
import ru.thirdcourse.courseproject.Shulmin.telegram.handlers.CallbackQueryHandler;
import ru.thirdcourse.courseproject.Shulmin.telegram.handlers.MessageHandler;
import ru.thirdcourse.courseproject.Shulmin.telegram.entity.Settings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public MyMathTelegramBot myMathTelegramBot(MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler) {
        SetWebhook setWebhook = SetWebhook.builder().url(webHookPath).build();

        return new MyMathTelegramBot(setWebhook, botToken, botName, messageHandler, callbackQueryHandler);
    }

    @Bean
    public Map<Long, Settings> settingsMap() {
        return new ConcurrentHashMap<>();
    }
}
