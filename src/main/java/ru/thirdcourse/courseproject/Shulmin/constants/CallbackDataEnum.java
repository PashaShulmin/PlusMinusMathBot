package ru.thirdcourse.courseproject.Shulmin.constants;

import java.util.ArrayList;
import java.util.List;

public enum CallbackDataEnum {
    // Классы
    FIRST_GRADE("1 класс"),
    SECOND_GRADE("2 класс"),
    THIRD_GRADE("3 класс"),
    FOURTH_GRADE("4 класс");

    private final String buttonName;

    CallbackDataEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public static List<String> getGrades() {
        List<String> grades = new ArrayList<>();

        grades.add(CallbackDataEnum.FIRST_GRADE.buttonName);
        grades.add(CallbackDataEnum.SECOND_GRADE.buttonName);
        grades.add(CallbackDataEnum.THIRD_GRADE.buttonName);
        grades.add(CallbackDataEnum.FOURTH_GRADE.buttonName);

        return grades;
    }
}
