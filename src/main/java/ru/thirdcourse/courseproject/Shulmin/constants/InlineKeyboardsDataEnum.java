package ru.thirdcourse.courseproject.Shulmin.constants;

import java.util.ArrayList;
import java.util.List;

public enum InlineKeyboardsDataEnum {
    // Классы
    GRADES_PREFIX("grades"),
    FIRST_GRADE("1grade"),
    SECOND_GRADE("2grade"),
    THIRD_GRADE("3grade"),
    FOURTH_GRADE("4grade"),

    // Классы текст на кнопках
    FIRST_GRADE_TEXT("1 класс"),
    SECOND_GRADE_TEXT("2 класс"),
    THIRD_GRADE_TEXT("3 класс"),
    FOURTH_GRADE_TEXT("4 класс"),

    // Префикс всех CallBackQuery.Data() у кнопок, отвечающих за выбор количества заданий
    TASKS_NUMBER_PREFIX("tasksNumber"),

    // Префикс всех CallBackQuery.Data() у кнопок, отвечающих за выбор количества заданий
    VARIANTS_NUMBER_PREFIX("variantsNumber"),

    // Форматы
    FORMAT_PREFIX("format"),
    DOCX(".docx (Word документ)"),
    TEXT_MESSAGE("В тексте ответного сообщения"),

    // 1-й класс темы текст на кнопках
    ADDITION_SUBTRACTION_1_9_TEXT("Сложение и вычитание чисел от 1 до 9 (без перехода через разряд)"),
    COMPARISON_1_9_TEXT("Сравнение чисел от 1 до 9"),
    ADDITION_SUBTRACTION_10_99_TEXT("Сложение и вычитание чисел от 10 до 99 (без перехода через разряд)"),
    COMPARISON_1_99_TEXT("Сравнение чисел от 1 до 99"),

    // 1-й класс темы
    ADDITION_SUBTRACTION_1_9("1.1"),
    COMPARISON_1_9("1.2"),
    ADDITION_SUBTRACTION_10_99("1.3"),
    COMPARISON_1_99("1.4"),

    // 2-й класс темы текст на кнопках
    ADDITION_SUBTRACTION_1_100_TEXT("Сложение и вычитание чисел от 1 до 100 (с переходом через разряд)"),
    EQUATION_PLUS_MINUS_100_TEXT("Уравнения сложение / вычитание (до 100, с переходом через разряд)"),
    MULTIPLICATION_TABLE_5_TEXT("Таблица умножения до 5 вкючительно"),
    DIVISION_TABLE_5_TEXT("Деление по таблице умножения до 5 вкючительно"),

    // 2-й класс темы
    ADDITION_SUBTRACTION_1_100("2.1"),
    EQUATION_PLUS_MINUS_100("2.2"),
    MULTIPLICATION_TABLE_5("2.3"),
    DIVISION_TABLE_5("2.4"),

    // 3-й класс темы текст на кнопках
    MULTIPLICATION_TABLE_FULL_TEXT("Таблица умножения полностью"),
    DIVISION_TABLE_FULL_TEXT("Деление по всей таблице умножения"),
    EQUATION_MULTIPLICATION_TABLE_TEXT("Уравнения по всей таблице умножения"),
    DIVISION_WITH_REMAINDER_TEXT("Деление с остатком по всей таблице умножения"),
    ADDITION_SUBTRACTION_BIG_NUMBERS_TEXT("Сложение / вычитание многозначный чисел"),
    // 3-й класс темы
    MULTIPLICATION_TABLE_FULL("3.1"),
    DIVISION_TABLE_FULL("3.2"),
    EQUATION_MULTIPLICATION_TABLE("3.3"),
    DIVISION_WITH_REMAINDER("3.4"),
    ADDITION_SUBTRACTION_BIG_NUMBERS("3.5");

    private final String content;

    InlineKeyboardsDataEnum(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getButtonCallbackQuery() {
        String name = this.name();
        if (name.endsWith("_TEXT")) {
            return InlineKeyboardsDataEnum.valueOf(name.replace("_TEXT", "")).content;
        }
        return content;
    }

    public static List<InlineKeyboardsDataEnum> getGrades() {
        List<InlineKeyboardsDataEnum> grades = new ArrayList<>();

        grades.add(InlineKeyboardsDataEnum.FIRST_GRADE_TEXT);
        grades.add(InlineKeyboardsDataEnum.SECOND_GRADE_TEXT);
        grades.add(InlineKeyboardsDataEnum.THIRD_GRADE_TEXT);
        grades.add(InlineKeyboardsDataEnum.FOURTH_GRADE_TEXT);

        return grades;
    }

    public static List<InlineKeyboardsDataEnum> getFirstGradeTopics() {
        List<InlineKeyboardsDataEnum> buttons = new ArrayList<>();

        buttons.add(InlineKeyboardsDataEnum.ADDITION_SUBTRACTION_1_9_TEXT);
        buttons.add(InlineKeyboardsDataEnum.COMPARISON_1_9_TEXT);
        buttons.add(InlineKeyboardsDataEnum.ADDITION_SUBTRACTION_10_99_TEXT);
        buttons.add(InlineKeyboardsDataEnum.COMPARISON_1_99_TEXT);

        return buttons;
    }

    public static List<InlineKeyboardsDataEnum> getSecondGradeTopics() {
        List<InlineKeyboardsDataEnum> buttons = new ArrayList<>();

        buttons.add(InlineKeyboardsDataEnum.ADDITION_SUBTRACTION_1_100_TEXT);
        buttons.add(InlineKeyboardsDataEnum.EQUATION_PLUS_MINUS_100_TEXT);
        buttons.add(InlineKeyboardsDataEnum.MULTIPLICATION_TABLE_5_TEXT);
        buttons.add(InlineKeyboardsDataEnum.DIVISION_TABLE_5_TEXT);

        return buttons;
    }

    public static List<InlineKeyboardsDataEnum> getThirdGradeTopics() {
        List<InlineKeyboardsDataEnum> buttons = new ArrayList<>();

        buttons.add(InlineKeyboardsDataEnum.MULTIPLICATION_TABLE_FULL_TEXT);
        buttons.add(InlineKeyboardsDataEnum.DIVISION_TABLE_FULL_TEXT);
        buttons.add(InlineKeyboardsDataEnum.EQUATION_MULTIPLICATION_TABLE_TEXT);
        buttons.add(InlineKeyboardsDataEnum.DIVISION_WITH_REMAINDER_TEXT);
        buttons.add(InlineKeyboardsDataEnum.ADDITION_SUBTRACTION_BIG_NUMBERS_TEXT);

        return buttons;
    }
}
