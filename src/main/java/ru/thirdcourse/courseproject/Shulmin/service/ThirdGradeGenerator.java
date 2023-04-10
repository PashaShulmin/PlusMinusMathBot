package ru.thirdcourse.courseproject.Shulmin.service;

import java.io.FileInputStream;
import java.io.IOException;

public interface ThirdGradeGenerator {
    FileInputStream GenerateDocx(String type, Long userId) throws IOException;

    String GenerateString(String type, Long userId);
}
