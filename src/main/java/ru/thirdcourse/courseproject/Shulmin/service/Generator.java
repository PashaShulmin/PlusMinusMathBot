package ru.thirdcourse.courseproject.Shulmin.service;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.thirdcourse.courseproject.Shulmin.PlusMinusMathBotApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public interface Generator {
    default void GenerateDocx(String type, Long userId) {
        List<List<String>> tasksAndAnswers = GenerateTasksAndAnswersAsListsOfStrings(type, userId);

        String absolutePath = PlusMinusMathBotApplication.class.getResource("/").getPath();
        try (FileOutputStream out = new FileOutputStream(absolutePath + "tasks.docx")) {
            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setFontSize(16);

            List<String> tasks = tasksAndAnswers.get(0);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).contains("Вариант")) {
                    if (i > 0) {
                        run.addBreak(BreakType.PAGE);
                    }
                    run.setText(tasks.get(i).substring(3, 12));
                    run.addBreak(BreakType.TEXT_WRAPPING);
                } else {
                    run.addBreak(BreakType.TEXT_WRAPPING);
                    run.setText(tasks.get(i));
                }
            }

            run.addBreak(BreakType.PAGE);
            run.setText("Ответы");
            run.addBreak(BreakType.TEXT_WRAPPING);

            for (String answer : tasksAndAnswers.get(1)) {
                if (answer.contains("Вариант")) {
                    run.addBreak(BreakType.TEXT_WRAPPING);
                    run.setText(answer.substring(3, 12));
                    run.addBreak(BreakType.TEXT_WRAPPING);
                } else if (answer.contains("&gt") || answer.contains("&lt")) {
                    String normAns = answer.replace("&gt", ">").replace("&lt", "<");
                    run.setText(normAns);
                } else {
                    run.setText(answer);
                }
                run.addBreak(BreakType.TEXT_WRAPPING);
            }

            doc.write(out);
            System.out.println("Docx was successfully created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String GenerateString(String type, Long userId);

    List<List<String>> GenerateTasksAndAnswersAsListsOfStrings(String type, Long userId);
}
