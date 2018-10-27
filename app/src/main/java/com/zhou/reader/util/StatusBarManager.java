package com.zhou.reader.util;

import android.content.Context;

import com.zhou.reader.App;

public class StatusBarManager {
    // 获得状态栏高度
    public static int getStatusBarHeight() {
        Context context = App.getAppContext();
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
