package com.zhou.reader;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getAppContext(){
        return application.getApplicationContext();
    }


}
