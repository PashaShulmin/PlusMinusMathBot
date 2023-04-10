package ru.thirdcourse.courseproject.Shulmin.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.thirdcourse.courseproject.Shulmin.constants.BotMessageEnum;
import ru.thirdcourse.courseproject.Shulmin.constants.MainMenuButtonsNameEnum;
import ru.thirdcourse.courseproject.Shulmin.repository.UserSettingsRepository;
import ru.thirdcourse.courseproject.Shulmin.telegram.keyboard.KeyboardMaker;
import ru.thirdcourse.courseproject.Shulmin.telegram.entity.Settings;

@Component
public class MessageHandler {
    private final KeyboardMaker keyboardMaker;
    private final UserSettingsRepository settingsRepo;

    public MessageHandler(KeyboardMaker keyboardMaker, UserSettingsRepository repository) {
        this.keyboardMaker = keyboardMaker;
        this.settingsRepo = repository;
    }

    public BotApiMethod<?> answerMessage(Message message) {
        Long chatId = message.getChatId();
        Long userId = message.getFrom().getId();

        String messageText = message.getText();

        if (messageText.equals("/start")) {
            setDefaultSettings(userId);
            return getStartMessage(chatId);
        } else if (messageText.equals(MainMenuButtonsNameEnum.GENERATE_EXERCISES_BUTTON.GetButtonName())) {
            Settings settings = settingsRepo.getWholeSettings(userId);
            if (settings == null) {
                return getUpdateSettingsMessage(chatId);
            } else {
                return getGenerateExercisesMessage(chatId);
            }
        } else if (messageText.equals(MainMenuButtonsNameEnum.SETTINGS_BUTTON.GetButtonName())) {
            return getSettingsMessage(chatId, userId);
        } else if (messageText.equals(MainMenuButtonsNameEnum.HELP_BUTTON.GetButtonName())) {
            return getHelpMessage(chatId);
        } else {
            return SendMessage.builder().chatId(chatId).text(BotMessageEnum.NON_COMMAND_MESSAGE.GetMessage()).parseMode("MarkdownV2").build();
        }
    }

    private void setDefaultSettings(Long userId) {
        Settings settings = new Settings();
        settingsRepo.setWholeSettings(userId, settings);
    }

    private BotApiMethod<?> getUpdateSettingsMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.UPDATE_BOT_MESSAGE.GetMessage())
                .build();
    }

    private BotApiMethod<?> getStartMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.HELP_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getMainMenuKeyboard())
                .parseMode("Markdown")
                .build();
    }

    private BotApiMethod<?> getGenerateExercisesMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.GENERATE_EXERCISES_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getSchoolGradesKeyboard())
                .parseMode("Markdown")
                .build();
    }

    private BotApiMethod<?> getSettingsMessage(Long chatId, Long userId) {
        Settings settings = settingsRepo.getWholeSettings(userId);
        if (settings == null) {
            settings = new Settings();
            setDefaultSettings(userId);
        }
        String text = BotMessageEnum.SETTINGS_MESSAGE.GetMessage()
                + "\n\n"
                + settings
                + "\n"
                + BotMessageEnum.CHOOSE_TASKS_NUMBER_MESSAGE.GetMessage();

        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .replyMarkup(keyboardMaker.getTasksNumberKeyboard())
                .parseMode("Markdown")
                .build();
    }

    private BotApiMethod<?> getHelpMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.HELP_MESSAGE.GetMessage())
                .parseMode("Markdown")
                .build();
    }
}
