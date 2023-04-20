package ru.thirdcourse.courseproject.Shulmin.service.implementations;

import org.springframework.stereotype.Service;
import ru.thirdcourse.courseproject.Shulmin.constants.InlineKeyboardsDataEnum;
import ru.thirdcourse.courseproject.Shulmin.repository.UserSettingsRepository;
import ru.thirdcourse.courseproject.Shulmin.service.ThirdGradeGenerator;
import ru.thirdcourse.courseproject.Shulmin.utils.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ThirdGradeGeneratorImpl implements ThirdGradeGenerator {
    private final UserSettingsRepository settingsRepo;
    private final Random random;

    public ThirdGradeGeneratorImpl(UserSettingsRepository settingsRepo) {
        this.settingsRepo = settingsRepo;

        Date date = new Date();
        random = new Random(date.getTime());
    }

    @Override
    public String GenerateString(String type, Long userId) {
        List<List<String>> tasks = GenerateTasksAndAnswersAsListsOfStrings(type, userId);

        return StringUtil.constructString(tasks);
    }

    @Override
    public List<List<String>> GenerateTasksAndAnswersAsListsOfStrings(String type, Long userId) {
        int tasksNumber = settingsRepo.getTasksNumber(userId);
        int variantsNumber = settingsRepo.getVariantsNumber(userId);

        List<List<String>> tasks;

        if (type.equals(InlineKeyboardsDataEnum.MULTIPLICATION_TABLE_FULL.getContent())) {
            tasks = GenerateMultiplicationTableFull(tasksNumber, variantsNumber);
        } else if (type.equals(InlineKeyboardsDataEnum.DIVISION_TABLE_FULL.getContent())) {
            tasks = GenerateDivisionTableFull(tasksNumber, variantsNumber);
        } else if (type.equals(InlineKeyboardsDataEnum.EQUATION_MULTIPLICATION_TABLE.getContent())) {
            tasks = GenerateEquationMultiplicationTable(tasksNumber, variantsNumber);
        } else if (type.equals(InlineKeyboardsDataEnum.DIVISION_WITH_REMAINDER.getContent())) {
            tasks = GenerateDivisionWithRemainder(tasksNumber, variantsNumber);
        } else {
            tasks = GenerateAdditionSubtractionBigNumbers(tasksNumber, variantsNumber);
        }

        return tasks;
    }

    private List<List<String>> GenerateMultiplicationTableFull(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d %s %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(1, 10);
                int y = random.nextInt(1, 10);
                tasks.add(String.format(template, j, x, "*", y));
                answers.add(j + ") " + (x * y));
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateDivisionTableFull(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d %s %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(1, 10);
                int y = random.nextInt(1, 10);
                tasks.add(String.format(template, j, x * y, ":", x));
                answers.add(j + ") " + y);
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateEquationMultiplicationTable(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %s %s %s = %s";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(1, 10);
                int y = random.nextInt(1, 10);
                int z = x * y;
                if (random.nextInt(2) == 0) {
                    switch (random.nextInt(3)) {
                        case 0 -> {
                            tasks.add(String.format(template, j, x, "*", y, "x"));
                            answers.add(j + ") " + (z));
                        }
                        case 1 -> {
                            tasks.add(String.format(template, j, x, "*", "x", z));
                            answers.add(j + ") " + (y));
                        }
                        case 2 -> {
                            tasks.add(String.format(template, j, "x", "*", y, z));
                            answers.add(j + ") " + (x));
                        }
                    }
                } else {
                    switch (random.nextInt(3)) {
                        case 0 -> {
                            tasks.add(String.format(template, j, z, ":", x, "x"));
                            answers.add(j + ") " + (y));
                        }
                        case 1 -> {
                            tasks.add(String.format(template, j, z, ":", "x", y));
                            answers.add(j + ") " + (x));
                        }
                        case 2 -> {
                            tasks.add(String.format(template, j, "x", ":", x, y));
                            answers.add(j + ") " + (z));
                        }
                    }
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateDivisionWithRemainder(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d : %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(2, 10);
                int y = random.nextInt(1, 10);
                int remainder = random.nextInt(1, x);
                tasks.add(String.format(template, j, x * y + remainder, x));
                answers.add(j + ") " + y + " остаток: " + remainder);
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateAdditionSubtractionBigNumbers(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d %s %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(10_000, 1_000_000);
                int y = random.nextInt(10_000, 1_000_000);
                if (random.nextInt(2) == 0) {
                    tasks.add(String.format(template, j, x, "+", y));
                    answers.add(j + ") " + (x + y));
                } else {
                    tasks.add(String.format(template, j, x + y, "-", x));
                    answers.add(j + ") " + y);
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }
}
