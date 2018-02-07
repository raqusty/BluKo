package com.raqust.bluko.common.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info:app相关的工具
 */

object AppUtil {

    /**
     * 判断app是不是在前台
     */
    fun  isBackground(context:Context ): Boolean{
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses
        Log.i("linzehao","context .packageName  "+context .packageName)
        appProcesses.forEach{
            Log.i("linzehao","it .processName  "+it.processName)
            return if (it .processName == context.packageName){
                it.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                true
            }else{
                false
            }
        }
        return false
    }

}