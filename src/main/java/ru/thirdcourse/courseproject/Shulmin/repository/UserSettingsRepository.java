package ru.thirdcourse.courseproject.Shulmin.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import ru.thirdcourse.courseproject.Shulmin.telegram.MyMathTelegramBot;
import ru.thirdcourse.courseproject.Shulmin.telegram.models.Settings;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserSettingsRepository {
    private final Map<Long, Settings> settingsMap;

    public UserSettingsRepository(Map<Long, Settings> settingsMap) {
        this.settingsMap = settingsMap;
    }

    public Settings getWholeSettings(Long userId) {
        return settingsMap.get(userId);
    }

    public int getTasksNumber(Long userId) {
        try {
            return settingsMap.get(userId).getTasksNumber();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getVariantsNumber(Long userId) {
        try {
            return settingsMap.get(userId).getVariantsNumber();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getTasksFormat(Long userId) {
        try {
            return settingsMap.get(userId).getTasksFormat();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setWholeSettings(Long userId, Settings settings) {
        settingsMap.put(userId, settings);
    }

    public void setTasksNumber(Long userId, int tasksNumber) {
        try {
            settingsMap.get(userId).setTasksNumber(tasksNumber);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void setVariantsNumber(Long userId, int variantsNumber) {
        try {
            settingsMap.get(userId).setVariantsNumber(variantsNumber);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void setTasksFormat(Long userId, String tasksFormat) {
        try {
            settingsMap.get(userId).setTasksFormat(tasksFormat);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
