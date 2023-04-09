package ru.thirdcourse.courseproject.Shulmin.constants;

public enum MainMenuButtonsNameEnum {
    GENERATE_EXERCISES_BUTTON("Сгенерировать задания"),
    SETTINGS_BUTTON("Настройки"),
    HELP_BUTTON("Помощь");

    private final String buttonName;

    MainMenuButtonsNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String GetButtonName() {
        return buttonName;
    }
}
