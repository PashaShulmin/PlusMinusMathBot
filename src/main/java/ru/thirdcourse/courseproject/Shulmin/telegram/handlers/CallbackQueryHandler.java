package ru.thirdcourse.courseproject.Shulmin.telegram.handlers;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.thirdcourse.courseproject.Shulmin.constants.BotMessageEnum;
import ru.thirdcourse.courseproject.Shulmin.constants.InlineKeyboardsDataEnum;
import ru.thirdcourse.courseproject.Shulmin.repository.UserSettingsRepository;
import ru.thirdcourse.courseproject.Shulmin.service.FirstGradeGenerator;
import ru.thirdcourse.courseproject.Shulmin.service.FourthGradeGenerator;
import ru.thirdcourse.courseproject.Shulmin.service.SecondGradeGenerator;
import ru.thirdcourse.courseproject.Shulmin.service.ThirdGradeGenerator;
import ru.thirdcourse.courseproject.Shulmin.telegram.MyMathTelegramBot;
import ru.thirdcourse.courseproject.Shulmin.telegram.entity.Settings;
import ru.thirdcourse.courseproject.Shulmin.telegram.keyboard.KeyboardMaker;

@Component
public class CallbackQueryHandler {
    private final KeyboardMaker keyboardMaker;
    private final UserSettingsRepository settingsRepo;
    private final FirstGradeGenerator firstGradeGenerator;
    private final SecondGradeGenerator secondGradeGenerator;
    private final ThirdGradeGenerator thirdGradeGenerator;
    private final FourthGradeGenerator fourthGradeGenerator;
    private final MyMathTelegramBot myMathTelegramBot;

    public CallbackQueryHandler(KeyboardMaker keyboardMaker, UserSettingsRepository repository,
                                FirstGradeGenerator firstGradeGenerator, SecondGradeGenerator secondGradeGenerator,
                                ThirdGradeGenerator thirdGradeGenerator, FourthGradeGenerator fourthGradeGenerator,
                                @Lazy MyMathTelegramBot myMathTelegramBot) {
        this.keyboardMaker = keyboardMaker;
        this.settingsRepo = repository;
        this.firstGradeGenerator = firstGradeGenerator;
        this.secondGradeGenerator = secondGradeGenerator;
        this.thirdGradeGenerator = thirdGradeGenerator;
        this.fourthGradeGenerator = fourthGradeGenerator;
        this.myMathTelegramBot = myMathTelegramBot;
    }

    public BotApiMethod<?> AnswerCallbackQuery(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getMessage().getChatId();
        Long userId = callbackQuery.getFrom().getId();

        String[] splitCallbackData = callbackQuery.getData().split(";");
        String prefix = splitCallbackData[0];
        String arg = null;
        if (splitCallbackData.length > 1) {
            arg = splitCallbackData[1];
        }

        Settings settings = settingsRepo.getWholeSettings(userId);
        if (settings == null) {
            return getUpdateSettingsMessage(chatId);
        }
        
        if (prefix.equals(InlineKeyboardsDataEnum.TASKS_NUMBER_PREFIX.getButtonCallbackQuery())) {
            settingsRepo.setTasksNumber(userId, Integer.parseInt(arg));
            return getVariantsNumberChooseMessage(chatId);
        } else if (prefix.equals(InlineKeyboardsDataEnum.VARIANTS_NUMBER_PREFIX.getButtonCallbackQuery())) {
            settingsRepo.setVariantsNumber(userId, Integer.parseInt(arg));
            return getTasksFormatChooseMessage(chatId);
        } else if (prefix.equals(InlineKeyboardsDataEnum.FORMAT_PREFIX.getButtonCallbackQuery())) {
            settingsRepo.setTasksFormat(userId, arg);
            String text = "Ваши настройки сохранены"
                    + "\n\n"
                    + settingsRepo.getWholeSettings(userId);
            return SendMessage.builder().chatId(chatId).text(text).parseMode("Markdown").build();
        } else if (prefix.equals(InlineKeyboardsDataEnum.GRADES_PREFIX.getContent())) {
            if (arg.equals(InlineKeyboardsDataEnum.FIRST_GRADE.getContent())) {
                return getFirstGradeChooseTopicMessage(chatId);
            } else if (arg.equals(InlineKeyboardsDataEnum.SECOND_GRADE.getContent())) {
                return getSecondGradeChooseTopicMessage(chatId);
            } else {
                return SendMessage.builder()
                        .chatId(chatId)
                        .text("*Ещё не обработано*")
                        .parseMode("Markdown")
                        .build();
            }
        } else if (prefix.equals(InlineKeyboardsDataEnum.FIRST_GRADE.getContent())) {
            return FirstGradeSendTasks(chatId, userId, arg);
        } else if (prefix.equals(InlineKeyboardsDataEnum.SECOND_GRADE.getContent())) {
            return SecondGradeSendTasks(chatId, userId, arg);
        } else {
            return SendMessage.builder()
                    .chatId(chatId)
                    .text("*Ещё не обработано*")
                    .parseMode("Markdown")
                    .build();
        }
    }

    private BotApiMethod<?> getUpdateSettingsMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.UPDATE_BOT_MESSAGE.GetMessage())
                .build();
    }

    private BotApiMethod<?> FirstGradeSendTasks(Long chatId, Long userId, String arg) {
        if (settingsRepo.getTasksFormat(userId).equals(InlineKeyboardsDataEnum.PDF.getContent())) {
            return null;
        } else {
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(firstGradeGenerator.GenerateString(arg, userId))
                    .parseMode("html")
                    .build();
        }
    }

    private BotApiMethod<?> SecondGradeSendTasks(Long chatId, Long userId, String arg) {
        if (settingsRepo.getTasksFormat(userId).equals(InlineKeyboardsDataEnum.PDF.getContent())) {
            return null;
        } else {
            return SendMessage.builder()
                    .chatId(chatId)
                    .text(secondGradeGenerator.GenerateString(arg, userId))
                    .parseMode("html")
                    .build();
        }
    }

    private BotApiMethod<?> getVariantsNumberChooseMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.CHOOSE_VARIANTS_NUMBER_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getVariantsNumberKeyboard())
                .parseMode("Markdown")
                .build();
    }

    private BotApiMethod<?> getTasksFormatChooseMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(BotMessageEnum.CHOOSE_TASKS_FORMAT_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getTasksFormatKeyboard())
                .parseMode("Markdown")
                .build();
    }

    private BotApiMethod<?> getFirstGradeChooseTopicMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(Long.toString(chatId))
                .text(BotMessageEnum.CHOOSE_TOPIC_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getFirstGradeTopicsKeyboard())
                .parseMode("Markdown")
                .build();
    }

    private BotApiMethod<?> getSecondGradeChooseTopicMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(Long.toString(chatId))
                .text(BotMessageEnum.CHOOSE_TOPIC_MESSAGE.GetMessage())
                .replyMarkup(keyboardMaker.getSecondGradeTopicsKeyboard())
                .parseMode("Markdown")
                .build();
    }
}
