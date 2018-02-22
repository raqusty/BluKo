package com.raqust.bluko;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.raqust.bluko.common.message.PushManager;
import com.raqust.bluko.common.net.NetUtils;
import com.raqust.bluko.common.utils.AppUtil;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks{
    public static MyApplication instance;


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        //用作扩展dex保存的方法数超过65K
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);
        NetUtils.init(instance);
        PushManager.INSTANCE.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        AppUtil.INSTANCE.addRunActivityCount();
    }

    @Override
    public void onActivityResumed(Activity activity) {


    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        AppUtil.INSTANCE.reduceRunActivityCount();
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
