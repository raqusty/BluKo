package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import android.content.DialogInterface




/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class SamsungRom : SystemRom() {

    override  val tag = "SamsungRom"

    //三星
    private val SAMSUNG = 0x30


    override  fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        //三星自启动应用程序管理
        Log.d("WhiteIntent", "三星手机")
        var samsungIntent: Intent? = Intent()
        samsungIntent!!.component = ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity")
        samsungIntent.putExtra("packageName", context.packageName)
        samsungIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.samsung.android.sm.ui.ram.AutoRunActivity跳转自启动管理页")
        if (WhiteIntentWrapper.doesActivityExists(context, samsungIntent)) {
            Log.d("WhiteIntent", "可通过com.samsung.android.sm.ui.ram.AutoRunActivity跳转自启动管理页")
        } else {
            Log.e("WhiteIntent", "不可通过com.samsung.android.sm.ui.ram.AutoRunActivity跳转自启动管理页")
            samsungIntent = context.packageManager.getLaunchIntentForPackage("com.samsung.android.sm")
            Log.d("WhiteIntent", "尝试通过getLaunchIntentForPackage(com.samsung.android.sm)跳转自启动管理页")
            if (samsungIntent != null) {
                Log.d("WhiteIntent", "可通过getLaunchIntentForPackage(com.samsung.android.sm)跳转自启动管理页")
                samsungIntent.putExtra("packageName", context.packageName)
                samsungIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                sIntentWrapperList.add(WhiteIntentWrapper(samsungIntent, SAMSUNG))
            } else {
                Log.e("WhiteIntent", "不可通过getLaunchIntentForPackage(com.samsung.android.sm)跳转自启动管理页")
                samsungIntent = Intent()
                samsungIntent.component = ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.BatteryActivity")
                samsungIntent.putExtra("packageName", context.packageName)
                samsungIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                Log.d("WhiteIntent", "尝试通过com.samsung.android.sm.ui.battery.BatteryActivity跳转自启动管理页")
                if (WhiteIntentWrapper.doesActivityExists(context, samsungIntent)) {
                    Log.d("WhiteIntent", "可通过com.samsung.android.sm.ui.battery.BatteryActivity跳转自启动管理页")
                    sIntentWrapperList.add(WhiteIntentWrapper(samsungIntent, SAMSUNG))
                } else {
                    Log.e("WhiteIntent", "不可通过com.samsung.android.sm.ui.battery.BatteryActivity跳转自启动管理页")
                    val bundle = Bundle()
                    bundle.putString("package", context.packageName)

                    samsungIntent = Intent()
                    samsungIntent.setClassName("com.android.settings",
                            "com.android.settings.Settings")
                    samsungIntent.action = Intent.ACTION_MAIN
                    samsungIntent.addCategory(Intent.CATEGORY_DEFAULT)
                    samsungIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    samsungIntent.putExtra(":android:show_fragment",
                            "com.android.settings.applications.AppOpsDetails")
                    samsungIntent.putExtra(":android:show_fragment_as_shortcut", false)
                    samsungIntent.putExtra(":android:show_fragment_args", bundle)
                    Log.d("WhiteIntent", "尝试通过com.android.settings.applications.AppOpsDetails跳转应用详情页-->" + samsungIntent.toString())
                    if (WhiteIntentWrapper.doesActivityExists(context, samsungIntent)) {
                        Log.d("WhiteIntent", "可通过com.android.settings.applications.AppOpsDetails跳转应用详情页")
                        sIntentWrapperList.add(WhiteIntentWrapper(samsungIntent, SYSTEM))
                    } else {
                        Log.e("WhiteIntent", "不可通过com.android.settings.applications.AppOpsDetails跳转应用详情页")
                    }
                }
            }
        }
    }

    override fun showDialog(reason:String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason,a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            DOZE -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_ss_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_ss_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
                            .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), DialogInterface.OnClickListener { d, w -> intent.startActivitySafely(a) })
                            .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                            .show()
                    wrapperList.add(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}