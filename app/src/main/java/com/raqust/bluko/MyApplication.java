package com.raqust.bluko;

import android.app.Application;
import android.content.Context;

import com.raqust.bluko.common.message.PushManager;
import com.raqust.bluko.common.net.NetUtils;

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
        NetUtils.init(instance);

        PushManager.INSTANCE.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
