package ru.thirdcourse.courseproject.Shulmin.telegram;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.thirdcourse.courseproject.Shulmin.telegram.handlers.CallbackQueryHandler;
import ru.thirdcourse.courseproject.Shulmin.telegram.handlers.MessageHandler;

public class MyMathTelegramBot extends SpringWebhookBot {
    private String botPath;
    private final String botUsername;
    private String botToken;

    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    public MyMathTelegramBot(SetWebhook setWebhook, String botToken, String botName, MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler) {
        super(setWebhook, botToken);
        botUsername = botName;
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return handleUpdate(update);
    }

    private BotApiMethod<?> handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            return callbackQueryHandler.AnswerCallbackQuery(update.getCallbackQuery());
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            return messageHandler.answerMessage(update.getMessage());
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
