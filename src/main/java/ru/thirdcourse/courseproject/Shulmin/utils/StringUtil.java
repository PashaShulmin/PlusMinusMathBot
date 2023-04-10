package ru.thirdcourse.courseproject.Shulmin.utils;

import java.util.List;

public class StringUtil {
    public static String constructString(List<List<String>> tasks) {
        StringBuilder sb = new StringBuilder();
        tasks.get(0).forEach(s -> sb.append(s).append("\n"));
        sb.append("\n").append("\t<b>Ответы</b>\n").append("<tg-spoiler>");
        tasks.get(1).forEach(s -> sb.append(s).append("\n"));
        sb.append("</tg-spoiler>");
        return sb.toString();
    }
}
