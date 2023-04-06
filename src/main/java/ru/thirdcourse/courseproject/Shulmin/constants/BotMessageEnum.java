package ru.thirdcourse.courseproject.Shulmin.constants;

public enum BotMessageEnum {
    // Ответы на запросы из основного меню
    GENERATE_EXERCISES_MESSAGE("Для начала, выберите класс."),
    SETTINGS_MESSAGE("Вы можете настроить количество вариантов и количество заданий в каждом варианте."),
    HELP_MESSAGE("Позже здесь появится информация о том, как пользоваться ботом."),

    // Настройки
    CHOOSE_TASKS_NUMBER_MESSAGE("Выберите количество заданий в одном варианте."),
    CHOOSE_VARIANTS_NUMBER_MESSAGE("Выберите количество вариантов."),

    // Ответ на нераспознанную команду
    NON_COMMAND_MESSAGE("Пожалуйста, воспользуйтесь клавиатурой.");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String GetMessage() {
        return message;
    }
}