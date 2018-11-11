package com.zhou.reader.ui.setting;

import android.graphics.Color;
import android.support.annotation.ColorInt;

public class ThemeManager {
    private static volatile ThemeManager sInstance;

    public static ThemeManager getInstance() {
        if (sInstance == null) {
            synchronized (ThemeManager.class) {
                if (sInstance == null) {
                    sInstance = new ThemeManager();
                }
            }
        }
        return sInstance;
    }


    @ColorInt
    public int getReadContentBackgroundColor() {
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        return isNightMode ? Color.parseColor("#1A1A1B") : Color.parseColor("#F5F5F5");
    }

    @ColorInt
    public int getReadContentTextColor() {
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        return isNightMode ? Color.parseColor("#686868") : Color.parseColor("#333333");
    }


    @ColorInt
    public int getHeadAndBottomBackgroundColor() {
        // 夜间模式 == 黑色 101115
        // 白天模式 == 蓝色 3F51B5
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        return isNightMode ? Color.parseColor("#101115") : Color.parseColor("#3F51B5");
    }

}
