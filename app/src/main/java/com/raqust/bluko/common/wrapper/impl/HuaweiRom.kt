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
class HuaweiRom :IRom{
    
    val tag = "HuaweiRom"

    //华为 自启管理
    private val HUAWEI = 0x10
    //华为 锁屏清理
    private val HUAWEI_GOD = 0x11

    override fun getIntent(context:Context,sIntentWrapperList:MutableList<WhiteIntentWrapper>) {
        var huaweiIntent = Intent()
        huaweiIntent.action = "huawei.intent.action.HSM_BOOTAPP_MANAGER"
        huaweiIntent.putExtra("packageName", context.packageName)
        huaweiIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d(tag, "尝试通过Action=huawei.intent.action.HSM_BOOTAPP_MANAGER跳转自启动设置")

        if (WhiteIntentWrapper.doesActivityExists(context, huaweiIntent)) {
            Log.d(tag, "可通过Action=huawei.intent.action.HSM_BOOTAPP_MANAGER跳转自启动设置")
          sIntentWrapperList.add(WhiteIntentWrapper(huaweiIntent, HUAWEI))
        } else {
            Log.e(tag, "不可通过Action==huawei.intent.action.HSM_BOOTAPP_MANAGER跳转自启动设置")
            huaweiIntent = Intent()
            huaweiIntent.component = ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")
            huaweiIntent.putExtra("packageName", context.packageName)
            huaweiIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Log.d(tag, "尝试通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转自启动设置")
            if (WhiteIntentWrapper.doesActivityExists(context, huaweiIntent)) {
                Log.d(tag, "可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转自启动设置")
                sIntentWrapperList.add(WhiteIntentWrapper(huaweiIntent, HUAWEI))
            } else {
                Log.e(tag, "不可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转自启动设置")
                huaweiIntent = Intent()
                huaweiIntent.component = ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity")
                huaweiIntent.putExtra("packageName", context.packageName)
                huaweiIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                Log.d(tag, "尝试通过com.huawei.permissionmanager.ui.MainActivity跳转自启动设置")
                if (WhiteIntentWrapper.doesActivityExists(context, huaweiIntent)) {
                    Log.d(tag, "可通过com.huawei.permissionmanager.ui.MainActivity跳转自启动设置")
                    sIntentWrapperList.add(WhiteIntentWrapper(huaweiIntent, HUAWEI))
                } else {
                    Log.e(tag, "不可通过com.huawei.permissionmanager.ui.MainActivity跳转自启动设置")
                }
            }
        }
        val huaweiGodIntent = Intent()
        huaweiGodIntent.component = ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")
        huaweiIntent.putExtra("packageName", context.packageName)
        huaweiIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d(tag, "尝试通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转锁屏清理白名单设置页")
        if (WhiteIntentWrapper.doesActivityExists(context, huaweiGodIntent)) {
            Log.d(tag, "可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转锁屏清理白名单设置页")
            sIntentWrapperList.add(WhiteIntentWrapper(huaweiGodIntent, HUAWEI_GOD))
        } else {
            Log.e(tag, "不可通过com.huawei.systemmanager.optimize.process.ProtectActivity跳转锁屏清理白名单设置页")
        }
    }

    override fun showDilog(a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
    }
}