package ru.thirdcourse.courseproject.Shulmin.telegram.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.thirdcourse.courseproject.Shulmin.constants.MainMenuButtonsNameEnum;
import ru.thirdcourse.courseproject.Shulmin.constants.CallbackDataEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardMaker {
    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(MainMenuButtonsNameEnum.GENERATE_EXERCISES_BUTTON.GetButtonName()));
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(new KeyboardButton(MainMenuButtonsNameEnum.SETTINGS_BUTTON.GetButtonName()));
        row.add(new KeyboardButton(MainMenuButtonsNameEnum.HELP_BUTTON.GetButtonName()));
        keyboard.add(row);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup getSchoolGradesKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : CallbackDataEnum.getGrades()) {
            keyboard.add(getLineWithOneButton(buttonName));
        }

        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    public InlineKeyboardMarkup getTasksNumberKeyboard() {
        final String callbackDataPrefix = CallbackDataEnum.TASKS_NUMBER_PREFIX.getButtonCallbackQuery() + ";";
        String[] tasksNumberVariants = new String[] {
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "15", "20", "25", "30", "35",
                "40", "45", "50", "60", "70",
                "80", "90", "100"
        };

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        int row = -1;
        for (int i = 0; i < tasksNumberVariants.length; i++) {
            if (i == 0 || i == 5 || i == 10 || i == 14 || i == 18 || i == 21) {
                row++;
                keyboard.add(new ArrayList<>());
            }
            String tasksNumberVariant = tasksNumberVariants[i];
            InlineKeyboardButton inlineKeyboardButton = InlineKeyboardButton.builder()
                    .text(tasksNumberVariant)
                    .callbackData(callbackDataPrefix + tasksNumberVariant)
                    .build();
            keyboard.get(row).add(inlineKeyboardButton);
        }

        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    public InlineKeyboardMarkup getVariantsNumberKeyboard() {
        final String callbackDataPrefix = CallbackDataEnum.VARIANTS_NUMBER_PREFIX.getButtonCallbackQuery() + ";";

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(new ArrayList<>());

        for (int i = 1; i <= 5; i++) {
            String variantsNumber = Integer.toString(i);
            InlineKeyboardButton inlineKeyboardButton = InlineKeyboardButton.builder()
                    .text(variantsNumber)
                    .callbackData(callbackDataPrefix + variantsNumber)
                    .build();
            keyboard.get(0).add(inlineKeyboardButton);
        }
        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    public ReplyKeyboard getTasksFormatKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(getLineWithOneButton("pdf", CallbackDataEnum.PDF.getButtonCallbackQuery()));
        keyboard.add(getLineWithOneButton("в тексте ответного сообщения",
                CallbackDataEnum.TEXT_MESSAGE.getButtonCallbackQuery()));

        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    private List<InlineKeyboardButton> getLineWithOneButton(String buttonName) {
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(buttonName)
                .callbackData(buttonName)
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }

    private List<InlineKeyboardButton> getLineWithOneButton(String buttonName, String callBackData) {
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(buttonName)
                .callbackData(callBackData)
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }
}
