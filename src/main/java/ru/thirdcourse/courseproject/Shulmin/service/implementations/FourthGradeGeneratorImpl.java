package ru.thirdcourse.courseproject.Shulmin.service.implementations;

import org.springframework.stereotype.Service;
import ru.thirdcourse.courseproject.Shulmin.repository.UserSettingsRepository;
import ru.thirdcourse.courseproject.Shulmin.service.FourthGradeGenerator;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class FourthGradeGeneratorImpl implements FourthGradeGenerator {
    private final UserSettingsRepository settingsRepo;
    private final Random random;

    public FourthGradeGeneratorImpl(UserSettingsRepository settingsRepo) {
        this.settingsRepo = settingsRepo;

        Date date = new Date();
        random = new Random(date.getTime());
    }

    @Override
    public String GenerateString(String type, Long userId) {
        return null;
    }

    @Override
    public List<List<String>> GenerateTasksAndAnswersAsListsOfStrings(String type, Long userId) {
        return null;
    }
}
