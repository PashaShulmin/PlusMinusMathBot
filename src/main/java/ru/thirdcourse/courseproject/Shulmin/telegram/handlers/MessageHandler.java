package ru.thirdcourse.courseproject.Shulmin.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.thirdcourse.courseproject.Shulmin.constants.BotMessageEnum;
import ru.thirdcourse.courseproject.Shulmin.constants.ButtonNameEnum;
import ru.thirdcourse.courseproject.Shulmin.telegram.keyboard.KeyboardMaker;

@Component
public class MessageHandler {
    private final KeyboardMaker keyboardMaker;

    public MessageHandler(KeyboardMaker keyboardMaker) {
        this.keyboardMaker = keyboardMaker;
    }

    public BotApiMethod<?> answerMessage(Message message) {
        Long chatId = message.getChatId();

        String messageText = message.getText();

        if (messageText.equals("/start")) {
            return GetStartMessage(chatId);
        } else if (messageText.equals(ButtonNameEnum.GENERATE_EXERCISES_BUTTON.GetButtonName())) {
            return GetGenerateExercisesMessage(chatId);
        } else if (messageText.equals(ButtonNameEnum.SETTINGS_BUTTON.GetButtonName())) {
            return GetSettingsMessage(chatId);
        } else if (messageText.equals(ButtonNameEnum.HELP_BUTTON.GetButtonName())) {
            return GetHelpMessage(chatId);
        } else {
            return SendMessage.builder().chatId(chatId).text(BotMessageEnum.NON_COMMAND_MESSAGE.GetMessage()).build();
        }
    }

    private BotApiMethod<?> GetStartMessage(Long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.HELP_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getMainMenuKeyboard())
                .build();
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    private BotApiMethod<?> GetGenerateExercisesMessage(Long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.GENERATE_EXERCISES_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getSchoolGradesKeyboard())
                .build();
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    private BotApiMethod<?> GetSettingsMessage(Long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.SETTINGS_MESSAGE.GetMessage() + "\n\n" + BotMessageEnum.CHOOSE_TASKS_NUMBER_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getTasksNumberMarkup())
                .build();
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    private BotApiMethod<?> GetHelpMessage(Long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.HELP_MESSAGE.GetMessage())
                .build();
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
