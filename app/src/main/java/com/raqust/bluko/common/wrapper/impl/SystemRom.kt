package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.util.Log
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import android.content.DialogInterface
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper.Companion.getApplicationName


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class SystemRom : IRom {

    val tag = "ZTERom"

    @RequiresApi(Build.VERSION_CODES.M)
    override  fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val ignoringBatteryOptimizations = pm.isIgnoringBatteryOptimizations(context.packageName)
        if (!ignoringBatteryOptimizations) {
            Log.d("WhiteIntent", "在电池优化白名单中")
            val dozeIntent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            dozeIntent.data = Uri.parse("package:" + context.packageName)
            if (WhiteIntentWrapper.doesActivityExists(context, dozeIntent)) {
                Log.d("WhiteIntent", "可以跳转到电池优化白名单设置页面")
                sIntentWrapperList.add(WhiteIntentWrapper(dozeIntent, WhiteIntentWrapper.DOZE))
            } else {
                Log.e("WhiteIntent", "不可跳转到电池优化白名单设置页面")
            }
        } else {
            Log.d("WhiteIntent", "不在电池优化白名单中")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun showDilog( a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        val pm = a.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (pm.isIgnoringBatteryOptimizations(a.getPackageName())) {
            return
        }

        try {
            AlertDialog.Builder(a)
                    .setCancelable(false)
                    .setTitle(WhiteIntentWrapper.getString(a, "reason_doze_title", WhiteIntentWrapper.getApplicationName(a)))
                    .setMessage(WhiteIntentWrapper.getString(a, "reason_doze_content", "", WhiteIntentWrapper.getApplicationName(a)))
                    .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), DialogInterface.OnClickListener { d, w -> intent.startActivitySafely(a) })
                    .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                    .show()
            wrapperList.add(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}