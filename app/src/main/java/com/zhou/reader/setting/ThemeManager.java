package com.zhou.reader.setting;

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
    public int getBackgroundColor() {
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        return isNightMode ? Color.WHITE : Color.BLACK;
    }

    @ColorInt
    public int getTextColor(){
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        return isNightMode ? Color.BLACK : Color.WHITE;
    }

}
