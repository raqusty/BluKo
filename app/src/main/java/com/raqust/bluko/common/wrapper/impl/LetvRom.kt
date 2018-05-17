package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import android.content.DialogInterface
import com.raqust.bluko.common.wrapper.DialogImpl


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class LetvRom : SystemRom() {

    override  val tag = "LetvRom"

    //乐视 自启动管理
    private val LETV = 0x90
    //乐视 应用保护
    private val LETV_GOD = 0x91

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        //乐视 自启动管理
        Log.d("WhiteIntent", "乐视手机")
        var letvIntent = Intent()
        letvIntent.action = "com.letv.android.permissionautoboot"
        letvIntent.putExtra("packageName", context.packageName)
        letvIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过Action=com.letv.android.permissionautoboot跳转自启动设置")
        if (WhiteIntentWrapper.doesActivityExists(context, letvIntent)) {
            Log.d("WhiteIntent", "可通过Action=com.letv.android.permissionautoboot跳转自启动设置")
            sIntentWrapperList.add(WhiteIntentWrapper(letvIntent, LETV))
        } else {
            Log.e("WhiteIntent", "不可通过Action=com.letv.android.permissionautoboot跳转自启动设置")
            letvIntent = Intent()
            letvIntent.component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")
            letvIntent.putExtra("packageName", context.packageName)
            letvIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Log.d("WhiteIntent", "尝试通过com.letv.android.letvsafe.AutobootManageActivity跳转自启动设置")
            if (WhiteIntentWrapper.doesActivityExists(context, letvIntent)) {
                Log.d("WhiteIntent", "可通过com.letv.android.letvsafe.AutobootManageActivity跳转自启动设置")
                sIntentWrapperList.add(WhiteIntentWrapper(letvIntent, LETV))
            } else {
                Log.e("WhiteIntent", "不可通过com.letv.android.letvsafe.AutobootManageActivity跳转自启动设置")
                letvIntent = Intent()
                letvIntent.component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps")
                letvIntent.putExtra("packageName", context.packageName)
                letvIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                Log.d("WhiteIntent", "尝试通过com.letv.android.letvsafe.PermissionAndApps跳转自启动设置")
                if (WhiteIntentWrapper.doesActivityExists(context, letvIntent)) {
                    Log.d("WhiteIntent", "可通过com.letv.android.letvsafe.PermissionAndApps跳转自启动设置")
                    sIntentWrapperList.add(WhiteIntentWrapper(letvIntent, LETV))
                } else {
                    Log.e("WhiteIntent", "不可通过com.letv.android.letvsafe.PermissionAndApps跳转自启动设置")
                }
            }
        }
        //乐视 应用保护
        val letvGodIntent = Intent()
        letvGodIntent.component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.BackgroundAppManageActivity")
        letvGodIntent.putExtra("packageName", context.packageName)
        letvGodIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.letv.android.letvsafe.BackgroundAppManageActivity跳转后台应用保护设置")
        if (WhiteIntentWrapper.doesActivityExists(context, letvGodIntent)) {
            Log.d("WhiteIntent", "可通过com.letv.android.letvsafe.BackgroundAppManageActivity跳转后台应用保护设置")
            sIntentWrapperList.add(WhiteIntentWrapper(letvGodIntent, LETV_GOD))
        } else {
            Log.e("WhiteIntent", "不可通过com.letv.android.letvsafe.BackgroundAppManageActivity跳转后台应用保护设置")
        }
    }

    override fun showDialog(reason:String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason,a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            LETV -> {
                DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_le_title", applicationName),
                        WhiteIntentWrapper.getString(a, "reason_le_content", reason, applicationName,applicationName),
                        WhiteIntentWrapper.getString(a, "ok"),
                        WhiteIntentWrapper.getString(a, "cancel"), {
                    intent.startActivitySafely(a)
                })
                wrapperList.add(intent)
            }
        }
    }
}