package com.zhou.reader;

import android.app.Application;
import android.content.Context;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.zhou.reader.db.MyObjectBox;
import com.zhou.reader.util.SelectorManager;

import io.objectbox.BoxStore;

public class App extends Application {
    private static Application application;
    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        SelectorManager.get().init();
        XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);

    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }


}
