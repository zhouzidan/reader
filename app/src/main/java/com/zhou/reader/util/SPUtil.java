package com.zhou.reader.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.zhou.reader.App;

public class SPUtil {

    public static void put(String name,int value){
        String packageName = App.getAppContext().getPackageName();
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(packageName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(name,value).apply();
    }

    public static int getInt(String name){
        String packageName = App.getAppContext().getPackageName();
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(packageName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(name,0);
    }

}
