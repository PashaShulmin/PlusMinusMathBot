package ru.thirdcourse.courseproject.Shulmin.service;

import java.io.FileInputStream;

public interface FourthGradeGenerator {
    FileInputStream GenerateDocx(String type, Long userId);

    String GenerateString(String type, Long userId);
}
