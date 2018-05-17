package com.raqust.bluko.common.wrapper.impl

import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.util.Log
import com.raqust.bluko.common.wrapper.DialogImpl
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import com.raqust.bluko.common.wrapper1.WhiteIntentWrapper1


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

    @TargetApi(Build.VERSION_CODES.M)
    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        //Android 7.0+ Doze 模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            val ignoringBatteryOptimizations = pm.isIgnoringBatteryOptimizations(context.packageName)
            if (!ignoringBatteryOptimizations) {
                Log.d("WhiteIntent", "在电池优化白名单中")
                val dozeIntent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                dozeIntent.data = Uri.parse("package:" + context.packageName)
                if (WhiteIntentWrapper.doesActivityExists(context, dozeIntent)) {
                    Log.d("WhiteIntent", "可以跳转到电池优化白名单设置页面")
                    sIntentWrapperList.add(WhiteIntentWrapper(dozeIntent, DOZE))
                } else {
                    Log.e("WhiteIntent", "不可跳转到电池优化白名单设置页面")
                }
            } else {
                Log.d("WhiteIntent", "不在电池优化白名单中")
            }
        }
    }

    override fun showDialog(reason: String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            DOZE -> {
                val pm = a.getSystemService(Context.POWER_SERVICE) as PowerManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pm.isIgnoringBatteryOptimizations(a.packageName)
                    DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_doze_title", applicationName),
                            WhiteIntentWrapper.getString(a, "reason_doze_content", reason, applicationName),
                            WhiteIntentWrapper.getString(a, "ok"),
                            WhiteIntentWrapper.getString(a, "cancel"), {
                        intent.startActivitySafely(a)
                    })
                    wrapperList.add(intent)
                }
            }
            SYSTEM -> {
                DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_system_title", applicationName),
                        WhiteIntentWrapper.getString(a, "reason_system_content", reason, applicationName),
                        WhiteIntentWrapper.getString(a, "ok"),
                        WhiteIntentWrapper.getString(a, "cancel"), {
                    intent.startActivitySafely(a)
                })
                wrapperList.add(intent)

            }
        }


    }
}