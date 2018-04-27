package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import android.content.DialogInterface




/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class LenovoRom : SystemRom() {

    override  val tag = "LetvRom"

    //联想 后台管理
    private val LENOVO = 0x100
    //联想 后台耗电优化
    private val LENOVO_GOD = 0x101

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        //联想 后台管理
        Log.d("WhiteIntent", "联想手机")
        val lenovoIntent = Intent()
        lenovoIntent.component = ComponentName("com.lenovo.security", "com.lenovo.security.purebackground.PureBackgroundActivity")
        lenovoIntent.putExtra("packageName", context.packageName)
        lenovoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.lenovo.security.purebackground.PureBackgroundActivity跳转后台管理页")
        if (WhiteIntentWrapper.doesActivityExists(context, lenovoIntent)) {
            Log.d("WhiteIntent", "尝试通过com.lenovo.security.purebackground.PureBackgroundActivity跳转后台管理页")
            sIntentWrapperList.add(WhiteIntentWrapper(lenovoIntent, LENOVO))
        } else {
            Log.e("WhiteIntent", "不可通过com.lenovo.security.purebackground.PureBackgroundActivity跳转后台管理页")
        }
        //联想 后台耗电优化
        val lenovoGodIntent = Intent()
        lenovoGodIntent.component = ComponentName("com.lenovo.powersetting", "com.lenovo.powersetting.ui.Settings\$HighPowerApplicationsActivity")
        lenovoIntent.putExtra("packageName", context.packageName)
        lenovoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.lenovo.powersetting.ui.Settings\$HighPowerApplicationsActivity跳转后台耗电优化")
        if (WhiteIntentWrapper.doesActivityExists(context, lenovoGodIntent)) {
            Log.d("WhiteIntent", "可通过com.lenovo.powersetting.ui.Settings\$HighPowerApplicationsActivity跳转后台耗电优化")
            sIntentWrapperList.add(WhiteIntentWrapper(lenovoGodIntent, LENOVO_GOD))
        } else {
            Log.e("WhiteIntent", "不可通过com.lenovo.powersetting.ui.Settings\$HighPowerApplicationsActivity跳转后台耗电优化")
        }
    }

    override fun showDilog(reason:String,a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDilog(reason,a, intent, wrapperList)
        when (intent.type) {
            LENOVO_GOD -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_lv_god_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_lv_god_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
                            .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), DialogInterface.OnClickListener { d, w -> intent.startActivitySafely(a) })
                            .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                            .show()
                    wrapperList.add(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            LENOVO -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_lv_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_lv_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
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