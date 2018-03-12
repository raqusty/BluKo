package com.raqust.bluko.common.utils

import android.app.ActivityManager
import android.app.AppOpsManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import android.util.Log
import java.util.*


/**
 * Created by linzehao
 * time: 2018/1/31.
 * info:app相关的工具
 */

object AppUtil {


    /**
     * app activity运行中的数量
     */
    private var isRunActivityCount = 0

    /**
     * app activity 增加 1
     */
    fun addRunActivityCount(){
        isRunActivityCount++
    }

    /**
     * app activity 减少 1
     */
    fun reduceRunActivityCount(){
        isRunActivityCount--
    }


    /**
     * 判断app是不是在前台
     */
    fun isBackground1(): Boolean {
        if(isRunActivityCount == 0 ){
            return true
        }
        return false
    }

    /**
     * 判断app是不是在前台
     */
    fun isBackground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses
        Log.i("linzehao", "context .packageName  " + context.packageName)
        appProcesses.forEach {
            Log.i("linzehao", "it .processName  " + it.processName)
            return if (it.processName == context.packageName) {
                it.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                true
            } else {
                false
            }
        }
        return false
    }


    /* 获得栈中最顶层的Activity
    *
    * @param context
    * @return
    */
   fun getTopActivity(context: Context): String? {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val useGranted = isUseGranted(context)
            Log.e("TopAppService", "use 权限 是否允许授权=" + useGranted)
            if (useGranted) {
                val topApp = getHigherClassName(context)
                Log.e("TopAppService", "顶层app=" + topApp)
                return topApp
            } else {
                //开启应用授权界面

//                val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
            }
        } else {
            val topApp = getLowerVersionClassName(context)
            Log.e("TopAppService", "顶层app=" + topApp)
            return topApp
        }
        return ""
    }

    /**
     * 判断  用户查看使用情况的权利是否给予app
     *
     * @return
     */
    private fun isUseGranted(context: Context): Boolean {
        val appOps = context
                .getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        var mode = -1
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mode = appOps.checkOpNoThrow("android:get_usage_stats",
                    android.os.Process.myUid(), context.getPackageName())
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun getHigherClassName(context: Context): String {
        var topPackageName = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val mUsageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
            val time = System.currentTimeMillis()
            //time - 1000 * 1000, time 开始时间和结束时间的设置，在这个时间范围内 获取栈顶Activity 有效
            val stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time)
            // Sort the stats by the last time used
            if (stats != null) {
                val mySortedMap = TreeMap<Long, UsageStats>()
                for (usageStats in stats) {
                    mySortedMap.put(usageStats.lastTimeUsed, usageStats)
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    topPackageName = mySortedMap.get(mySortedMap.lastKey())!!.getPackageName()
                    Log.e("TopPackage Name", topPackageName)
                }
            }
        } else {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val topActivity = activityManager.getRunningTasks(1)[0].topActivity
            topPackageName = topActivity.className
        }
        return topPackageName
    }

    private fun getLowerVersionClassName(context: Context): String {
        val topPackageName: String//低版本  直接获取getRunningTasks
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val topActivity = activityManager.getRunningTasks(1)[0].topActivity
        topPackageName = topActivity.className
        return topPackageName
    }

}