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
        return isNightMode ? Color.parseColor("#1A1A1B") : Color.parseColor("#F5F5F5");
    }

    @ColorInt
    public int getTextColor(){
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        return isNightMode ? Color.parseColor("#686868") : Color.parseColor("#333333");
    }


    @ColorInt
    public int getTitleAndBottomColor(){
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        return isNightMode ? Color.parseColor("#FAFAFA") : Color.parseColor("#101115");
    }



}
