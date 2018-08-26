package com.zhou.reader;

import android.app.Application;
import android.content.Context;

import com.zhou.reader.entity.MyObjectBox;

import io.objectbox.BoxStore;

public class App extends Application {
    private static Application application;
    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
    }

    public static Context getAppContext(){
        return application.getApplicationContext();
    }

    public static BoxStore getBoxStore(){
        return boxStore;
    }


}
