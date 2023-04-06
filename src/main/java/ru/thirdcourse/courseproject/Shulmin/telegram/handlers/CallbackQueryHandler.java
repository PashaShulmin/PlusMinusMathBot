package ru.thirdcourse.courseproject.Shulmin.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.thirdcourse.courseproject.Shulmin.telegram.keyboard.KeyboardMaker;

@Component
public class CallbackQueryHandler {
    private final KeyboardMaker keyboardMaker;

    public CallbackQueryHandler(KeyboardMaker keyboardMaker) {
        this.keyboardMaker = keyboardMaker;
    }

    public BotApiMethod<?> AnswerCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }
}
