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
    FORMAT_PREFIX("format"),
    PDF("pdf"),
    TEXT_MESSAGE("В тексте ответного сообщения"),

    // 1-й класс темы
    ADDITION_SUBTRACTION_1_9("Сложение и вычитание чисел от 1 до 9 (без перехода через разряд)"),
    COMPARISON_1_9("Сравнение чисел от 1 до 9"),
    ADDITION_SUBTRACTION_10_99("Сложение и вычитание чисел от 10 до 99 (без перехода через разряд)"),
    COMPARISON_1_99("Сравнение чисел от 1 до 99");

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

    public static List<String> getFirstGradeTopics() {
        List<String> grades = new ArrayList<>();

        grades.add(CallbackDataEnum.ADDITION_SUBTRACTION_1_9.buttonCallbackQuery);
        grades.add(CallbackDataEnum.COMPARISON_1_9.buttonCallbackQuery);
        grades.add(CallbackDataEnum.ADDITION_SUBTRACTION_10_99.buttonCallbackQuery);
        grades.add(CallbackDataEnum.COMPARISON_1_99.buttonCallbackQuery);

        return grades;
    }
}
