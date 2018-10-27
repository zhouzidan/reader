package com.zhou.reader.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Window;

import com.zhou.reader.App;

public class StatusBarManager {
    // 获得状态栏高度
    public static int getStatusBarHeight() {
        Context context = App.getAppContext();
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 设置StatusBar的颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBar(Window window, @ColorInt int color) {
        if (window == null) return;
        window.setStatusBarColor(color);
    }
}
