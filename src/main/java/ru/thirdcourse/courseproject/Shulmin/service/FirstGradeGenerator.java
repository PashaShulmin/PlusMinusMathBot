package ru.thirdcourse.courseproject.Shulmin.service;

import java.io.FileInputStream;

public interface FirstGradeGenerator {
    FileInputStream GenerateDocx(String type, Long userId);

    String GenerateString(String type, Long userId);
}
