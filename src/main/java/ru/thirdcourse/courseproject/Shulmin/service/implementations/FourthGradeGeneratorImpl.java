package ru.thirdcourse.courseproject.Shulmin.service.implementations;

import org.springframework.stereotype.Service;
import ru.thirdcourse.courseproject.Shulmin.service.FourthGradeGenerator;

import java.io.FileInputStream;

@Service
public class FourthGradeGeneratorImpl implements FourthGradeGenerator {
    @Override
    public FileInputStream GenerateDocx(String type, Long userId) {
        return null;
    }

    @Override
    public String GenerateString(String type, Long userId) {
        return null;
    }
}
