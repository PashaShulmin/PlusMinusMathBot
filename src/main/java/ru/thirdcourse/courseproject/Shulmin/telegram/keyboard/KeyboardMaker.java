package ru.thirdcourse.courseproject.Shulmin.telegram.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.thirdcourse.courseproject.Shulmin.constants.ButtonNameEnum;
import ru.thirdcourse.courseproject.Shulmin.constants.CallbackDataEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardMaker {
    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.GENERATE_EXERCISES_BUTTON.GetButtonName()));
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(new KeyboardButton(ButtonNameEnum.SETTINGS_BUTTON.GetButtonName()));
        row.add(new KeyboardButton(ButtonNameEnum.HELP_BUTTON.GetButtonName()));
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

    public InlineKeyboardMarkup getTasksNumberMarkup() {
        final String callbackDataPrefix = "tasksNumber;";
        String[] tasksNumberVariants = new String[] {
                "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "15", "20", "25", "30", "35",
                "40", "45", "50", "60", "70",
                "80", "90", "100"
        };

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>(1);

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

    private List<InlineKeyboardButton> getLineWithOneButton(String buttonName) {
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(buttonName)
                .callbackData(buttonName)
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }
}
