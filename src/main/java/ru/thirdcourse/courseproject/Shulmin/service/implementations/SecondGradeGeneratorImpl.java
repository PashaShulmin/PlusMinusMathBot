package ru.thirdcourse.courseproject.Shulmin.service.implementations;

import org.springframework.stereotype.Service;
import ru.thirdcourse.courseproject.Shulmin.constants.InlineKeyboardsDataEnum;
import ru.thirdcourse.courseproject.Shulmin.repository.UserSettingsRepository;
import ru.thirdcourse.courseproject.Shulmin.service.SecondGradeGenerator;
import ru.thirdcourse.courseproject.Shulmin.utils.StringUtil;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SecondGradeGeneratorImpl implements SecondGradeGenerator {
    private final UserSettingsRepository settingsRepo;
    private final Random random;

    public SecondGradeGeneratorImpl(UserSettingsRepository settingsRepo) {
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

        if (type.equals(InlineKeyboardsDataEnum.ADDITION_SUBTRACTION_1_100.getContent())) {
            tasks = GenerateAdditionSubtraction1100(tasksNumber, variantsNumber);
        } else if (type.equals(InlineKeyboardsDataEnum.EQUATION_PLUS_MINUS_100.getContent())) {
            tasks = GenerateEquationPlusMinus100(tasksNumber, variantsNumber);
        } else if (type.equals(InlineKeyboardsDataEnum.MULTIPLICATION_TABLE_5.getContent())) {
            tasks = GenerateMultiplicationTable5(tasksNumber, variantsNumber);
        } else {
            tasks = GenerateDivisionTable5(tasksNumber, variantsNumber);
        }

        return tasks;
    }

    private List<List<String>> GenerateAdditionSubtraction1100(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d %s %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(1, 100);
                int y = random.nextInt(1, 100 - x + 1);
                if (random.nextInt(2) == 0) {
                    tasks.add(String.format(template, j, x, "+", y));
                    answers.add(j + ") " + (x + y));
                } else {
                    int buf = Integer.min(x, y);
                    x = Integer.max(x, y);
                    y = buf;
                    tasks.add(String.format(template, j, x, "-", y));
                    answers.add(j + ") " + (x - y));
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateEquationPlusMinus100(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %s %s %s = %s";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(1, 100);
                int y = random.nextInt(1, 100 - x + 1);
                if (random.nextInt(2) == 0) {
                    int z = x + y;
                    switch (random.nextInt(3)) {
                        case 0 -> {
                            tasks.add(String.format(template, j, x, "+", y, "x"));
                            answers.add(j + ") " + (x + y));
                        }
                        case 1 -> {
                            tasks.add(String.format(template, j, x, "+", "x", z));
                            answers.add(j + ") " + (z - x));
                        }
                        case 2 -> {
                            tasks.add(String.format(template, j, "x", "+", y, z));
                            answers.add(j + ") " + (z - y));
                        }
                    }
                } else {
                    int buf = Integer.min(x, y);
                    x = Integer.max(x, y);
                    y = buf;
                    int z = x - y;
                    switch (random.nextInt(3)) {
                        case 0 -> {
                            tasks.add(String.format(template, j, x, "-", y, "x"));
                            answers.add(j + ") " + (x - y));
                        }
                        case 1 -> {
                            tasks.add(String.format(template, j, x, "-", "x", z));
                            answers.add(j + ") " + (x - z));
                        }
                        case 2 -> {
                            tasks.add(String.format(template, j, "x", "-", y, z));
                            answers.add(j + ") " + (z + y));
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

    private List<List<String>> GenerateMultiplicationTable5(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d %s %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                List<Integer> args = getMultiplicationArgs();
                tasks.add(String.format(template, j, args.get(0), "*", args.get(1)));
                answers.add(j + ") " + args.get(2));
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateDivisionTable5(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d %s %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                List<Integer> args = getMultiplicationArgs();
                tasks.add(String.format(template, j, args.get(2), ":", args.get(0)));
                answers.add(j + ") " + args.get(1));
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<Integer> getMultiplicationArgs() {
        int x = random.nextInt(1, 6);
        int y = random.nextInt(1, 10);
        int z = x * y;
        if (random.nextInt(2) == 0) {
            int buf = x;
            x = y;
            y = buf;
        }
        return List.of(new Integer[]{x, y, z});
    }
}
