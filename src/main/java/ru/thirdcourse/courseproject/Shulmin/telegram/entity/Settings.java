package ru.thirdcourse.courseproject.Shulmin.telegram.entity;

import java.util.Objects;

public final class Settings {
    private int tasksNumber;
    private int variantsNumber;
    private String tasksFormat;

    public Settings() {
        tasksNumber = 10;
        variantsNumber = 1;
        tasksFormat = "textMessage";
    }

    public int getTasksNumber() {
        return tasksNumber;
    }

    public int getVariantsNumber() {
        return variantsNumber;
    }

    public String getTasksFormat() {
        return tasksFormat;
    }

    public void setTasksNumber(int tasksNumber) {
        this.tasksNumber = tasksNumber;
    }

    public void setVariantsNumber(int variantsNumber) {
        this.variantsNumber = variantsNumber;
    }

    public void setTasksFormat(String tasksFormat) {
        this.tasksFormat = tasksFormat;
    }

    @Override
    public String toString() {
        String taskFormatToString = tasksFormat;
        if (Objects.equals(tasksFormat, "textMessage")) {
            taskFormatToString = "в тексте ответного сообщения";
        }

        return "Текущие настройки\n" +
                "Количество заданий в одном варианте: *" +
                tasksNumber +
                "*\n" +
                "Количество вариантов: *" +
                variantsNumber +
                "*\n" +
                "В каком формате присылать задания: *" +
                taskFormatToString +
                "*\n";
    }
}
