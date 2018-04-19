package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class LGRom : IRom {

    val tag = "LGRom"

    //LG 自启管理
    private val LG = 0x130

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        //LG自启动应用程序管理
        Log.d("WhiteIntent", "LG手机")
        val lgIntent = Intent("android.intent.action.MAIN")
        lgIntent.component = ComponentName("com.android.settings", "com.android.settings.Settings\$AccessLockSummaryActivity")
        lgIntent.putExtra("packageName", context.packageName)
        lgIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.android.settings.Settings\$AccessLockSummaryActivity跳转自启动设置")
        if (WhiteIntentWrapper.doesActivityExists(context, lgIntent)) {
            Log.d("WhiteIntent", "可通过com.android.settings.Settings\$AccessLockSummaryActivity跳转自启动设置")
            sIntentWrapperList.add(WhiteIntentWrapper(lgIntent, LG))
        } else {
            Log.e("WhiteIntent", "不可通过com.android.settings.Settings\$AccessLockSummaryActivity跳转自启动设置")
        }
    }

    override fun showDilog(a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
    }
}