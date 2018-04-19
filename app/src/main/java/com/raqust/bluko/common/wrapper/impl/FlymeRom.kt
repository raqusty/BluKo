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
class FlymeRom : IRom {

    val tag = "FlymeRom"

    //魅族 自启动管理
    private val MEIZU = 0x40
    //魅族 待机耗电管理
    private val MEIZU_GOD = 0x41

    override    fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        //魅族 自启动管理
        Log.d("WhiteIntent", "魅族手机")
        var meizuIntent = Intent("com.meizu.safe.security.SHOW_APPSEC")
        meizuIntent.addCategory(Intent.CATEGORY_DEFAULT)
        meizuIntent.putExtra("packageName", context.packageName)
        meizuIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过Action=com.meizu.safe.security.SHOW_APPSEC跳转自启动设置")
        if (WhiteIntentWrapper.doesActivityExists(context, meizuIntent)) {
            Log.d("WhiteIntent", "可通过Action=com.meizu.safe.security.SHOW_APPSEC跳转自启动设置")
            sIntentWrapperList.add(WhiteIntentWrapper(meizuIntent, MEIZU))
        } else {
            Log.e("WhiteIntent", "不可通过Action=com.meizu.safe.security.SHOW_APPSEC跳转自启动设置")
            meizuIntent = Intent()
            meizuIntent.component = ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity")
            meizuIntent.putExtra("packageName", context.packageName)
            meizuIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Log.d("WhiteIntent", "尝试通过com.meizu.safe.permission.PermissionMainActivity跳转自启动设置")
            if (WhiteIntentWrapper.doesActivityExists(context, meizuIntent)) {
                Log.d("WhiteIntent", "可通过com.meizu.safe.permission.PermissionMainActivity跳转自启动设置")
                sIntentWrapperList.add(WhiteIntentWrapper(meizuIntent, MEIZU))
            } else {
                Log.e("WhiteIntent", "不可通过com.meizu.safe.permission.PermissionMainActivity跳转自启动设置")
            }
        }
        //魅族 待机耗电管理
        val meizuGodIntent = Intent()
        meizuGodIntent.component = ComponentName("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity")
        meizuGodIntent.putExtra("packageName", context.packageName)
        meizuGodIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.meizu.safe.powerui.PowerAppPermissionActivity跳转待机耗电管理页")
        if (WhiteIntentWrapper.doesActivityExists(context, meizuGodIntent)) {
            Log.d("WhiteIntent", "可通过com.meizu.safe.powerui.PowerAppPermissionActivity跳转待机耗电管理页")
            sIntentWrapperList.add(WhiteIntentWrapper(meizuGodIntent, MEIZU_GOD))
        } else {
            Log.e("WhiteIntent", "不可通过com.meizu.safe.powerui.PowerAppPermissionActivity跳转待机耗电管理页")
        }
    }

    override fun showDilog(a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
    }

}