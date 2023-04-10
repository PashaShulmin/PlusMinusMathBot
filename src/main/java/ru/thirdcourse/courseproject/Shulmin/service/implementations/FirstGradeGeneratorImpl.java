package ru.thirdcourse.courseproject.Shulmin.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.thirdcourse.courseproject.Shulmin.constants.InlineKeyboardsDataEnum;
import ru.thirdcourse.courseproject.Shulmin.repository.UserSettingsRepository;
import ru.thirdcourse.courseproject.Shulmin.service.FirstGradeGenerator;
import ru.thirdcourse.courseproject.Shulmin.utils.StringUtil;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@Service
@Slf4j
public class FirstGradeGeneratorImpl implements FirstGradeGenerator {
    private final UserSettingsRepository settingsRepo;
    private final Random random;

    public FirstGradeGeneratorImpl(UserSettingsRepository settingsRepo) {
        this.settingsRepo = settingsRepo;

        Date date = new Date();
        random = new Random(date.getTime());
    }

    public FileInputStream GenerateDocx(String type, Long userId) {
        /*Document doc = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Tasks.pdf"));
            log.info("pdf created");
            doc.open();
            doc.add(new Paragraph(GenerateString(type, userId)));
            doc.close();
            writer.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        return new FileInputStream("Tasks.pdf");*/
        return null;
    }

    @Override
    public String GenerateString(String type, Long userId) {
        int tasksNumber = settingsRepo.getTasksNumber(userId);
        int variantsNumber = settingsRepo.getVariantsNumber(userId);

        List<List<String>> tasks;

        if (type.equals(InlineKeyboardsDataEnum.ADDITION_SUBTRACTION_1_9.getContent())) {
            tasks = GenerateAdditionSubtraction19(tasksNumber, variantsNumber);
        } else if (type.equals(InlineKeyboardsDataEnum.COMPARISON_1_9.getContent())) {
            tasks = GenerateComparison19(tasksNumber, variantsNumber);
        } else if (type.equals(InlineKeyboardsDataEnum.ADDITION_SUBTRACTION_10_99.getContent())) {
            tasks = GenerateAdditionSubtraction1099(tasksNumber, variantsNumber);
        } else {
            tasks = GenerateComparison199(tasksNumber, variantsNumber);
        }

        return StringUtil.constructString(tasks);
    }

    private List<List<String>> GenerateAdditionSubtraction19(int tasksNumber, int variantsNumber) {
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
                int y = random.nextInt(1, 10 - x + 1);
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

    private List<List<String>> GenerateComparison19(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d ? %d";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(1, 10);
                int y;
                if (x == 1) y = random.nextInt(2, 10);
                else if (x == 9) y = random.nextInt(1, 9);
                else if (random.nextInt(2) == 0) y = random.nextInt(1, x);
                else y = random.nextInt(x + 1, 10);
                tasks.add(String.format(template, j, x, y));
                String ans = x < y ? "&lt" : "&gt";
                answers.add(j + ") " + ans);
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateAdditionSubtraction1099(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d %s %d =";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(10, 90);
                int ones = x % 10 == 9 ? 0 : random.nextInt(1, 10 - x % 10);
                int y = random.nextInt(1, 10 - x / 10) * 10 + ones;
                int sum = x + y;
                if (random.nextInt(2) == 0) {
                    tasks.add(String.format(template, j, x, "+", y));
                    answers.add(j + ") " + (x + y));
                } else {
                    tasks.add(String.format(template, j, sum, "-", x));
                    answers.add(j + ") " + (sum - x));
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }

    private List<List<String>> GenerateComparison199(int tasksNumber, int variantsNumber) {
        List<String> tasks = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        String template = "%d) %d ? %d";

        for (int i = 1; i <= variantsNumber; i++) {
            if (variantsNumber > 1) {
                tasks.add("<b>Вариант " + i + "</b>");
                answers.add("<b>Вариант " + i + "</b>");
            }
            for (int j = 1; j <= tasksNumber; j++) {
                int x = random.nextInt(1, 100);
                int y;
                if (x == 1) y = random.nextInt(2, 100);
                else if (x == 99) y = random.nextInt(1, 99);
                else if (random.nextInt(2) == 0) y = random.nextInt(1, x);
                else y = random.nextInt(x + 1, 100);
                tasks.add(String.format(template, j, x, y));
                String ans = x < y ? "&lt" : "&gt";
                answers.add(j + ") " + ans);
            }
        }
        List<List<String>> result = new ArrayList<>();
        result.add(tasks);
        result.add(answers);
        return result;
    }
}
