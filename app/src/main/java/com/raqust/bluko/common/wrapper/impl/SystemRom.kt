package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
open class SystemRom : IRom {

    protected open val tag = "SystemRom"

    //Android 7.0+ Doze 模式
    val SYSTEM = 0x00
    val DOZE = 0x01

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val ignoringBatteryOptimizations = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pm.isIgnoringBatteryOptimizations(context.packageName)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        if (!ignoringBatteryOptimizations) {
            Log.d("WhiteIntent", "在电池优化白名单中")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val dozeIntent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                dozeIntent.data = Uri.parse("package:" + context.packageName)
                if (WhiteIntentWrapper.doesActivityExists(context, dozeIntent)) {
                    Log.d("WhiteIntent", "可以跳转到电池优化白名单设置页面")
                    sIntentWrapperList.add(WhiteIntentWrapper(dozeIntent, DOZE))
                } else {
                    Log.e("WhiteIntent", "不可跳转到电池优化白名单设置页面")
                }
            }
        } else {
            Log.d("WhiteIntent", "不在电池优化白名单中")
        }
    }

    override fun showDilog(reason: String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        when (intent.type) {
            DOZE -> {
                val pm = a.getSystemService(Context.POWER_SERVICE) as PowerManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pm.isIgnoringBatteryOptimizations(a.packageName)
                    try {
                        AlertDialog.Builder(a)
                                .setCancelable(false)
                                .setTitle(WhiteIntentWrapper.getString(a, "reason_doze_title", WhiteIntentWrapper.getApplicationName(a)))
                                .setMessage(WhiteIntentWrapper.getString(a, "reason_doze_content", "", WhiteIntentWrapper.getApplicationName(a)))
                                .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), { d, w -> intent.startActivitySafely(a) })
                                .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                                .show()
                        wrapperList.add(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            SYSTEM -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_system_title", WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_system_content", reason, WhiteIntentWrapper.getApplicationName(a)))
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