package com.bei.rronet;

import android.app.Application;
import android.content.Context;

/**
 * @author: created by ZhaoBeibei on 2020-01-02 14:15
 * @describe:
 */
public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }


    public static Context getContext() {
        return sContext;
    }
}
