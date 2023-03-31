package ru.thirdcourse.courseproject.Shulmin.telegram;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

public class MyMathTelegramBot extends SpringWebhookBot {
    private String botPath;
    private String botUsername;
    private String botToken;

    public MyMathTelegramBot(SetWebhook setWebhook, String botToken, String botName) {
        super(setWebhook, botToken);
        botUsername = botName;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();

            try {
                execute(SendMessage.builder().chatId(chatId).text("Success").build());
            } catch (TelegramApiException ex)
            {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
