package com.raqust.bluko;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication instance;


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //用作扩展dex保存的方法数超过65K
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
