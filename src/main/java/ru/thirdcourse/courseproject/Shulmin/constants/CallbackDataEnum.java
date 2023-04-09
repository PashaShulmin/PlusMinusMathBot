package ru.thirdcourse.courseproject.Shulmin.constants;

import java.util.ArrayList;
import java.util.List;

public enum CallbackDataEnum {
    // Классы
    FIRST_GRADE("1 класс"),
    SECOND_GRADE("2 класс"),
    THIRD_GRADE("3 класс"),
    FOURTH_GRADE("4 класс"),

    // Префикс всех CallBackQuery.Data() у кнопок, отвечающих за выбор количества заданий
    TASKS_NUMBER_PREFIX("tasksNumber"),

    // Префикс всех CallBackQuery.Data() у кнопок, отвечающих за выбор количества заданий
    VARIANTS_NUMBER_PREFIX("variantsNumber"),

    // Форматы
    PDF("pdf"),
    TEXT_MESSAGE("textMessage");

    private final String buttonCallbackQuery;

    CallbackDataEnum(String buttonCallbackQuery) {
        this.buttonCallbackQuery = buttonCallbackQuery;
    }

    public String getButtonCallbackQuery() {
        return buttonCallbackQuery;
    }

    public static List<String> getGrades() {
        List<String> grades = new ArrayList<>();

        grades.add(CallbackDataEnum.FIRST_GRADE.buttonCallbackQuery);
        grades.add(CallbackDataEnum.SECOND_GRADE.buttonCallbackQuery);
        grades.add(CallbackDataEnum.THIRD_GRADE.buttonCallbackQuery);
        grades.add(CallbackDataEnum.FOURTH_GRADE.buttonCallbackQuery);

        return grades;
    }
}
