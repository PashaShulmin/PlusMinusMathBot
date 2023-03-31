package ru.thirdcourse.courseproject.Shulmin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.thirdcourse.courseproject.Shulmin.telegram.MyMathTelegramBot;

@RestController
public class WebHookController {
    private final MyMathTelegramBot telegramBot;

    public WebHookController(MyMathTelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping(value = "/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
}
