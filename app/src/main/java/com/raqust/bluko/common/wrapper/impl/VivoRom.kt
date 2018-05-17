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
class VivoRom : SystemRom() {

    override val tag = "VivoRom"

    //Vivo 后台高耗电
    private val VIVO = 0x60
    private val VIVO_GOD = 0x61

    override   fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        //Vivo 后台高耗电
        Log.d("WhiteIntent", "Vivo手机")
        var vivoIntent = Intent()
        vivoIntent.component = ComponentName.unflattenFromString("com.iqoo.secure/.ui.phoneoptimize.AddWhiteListActivity")
        vivoIntent.putExtra("packageName", context.packageName)
        vivoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity跳转自启动设置")
        if (WhiteIntentWrapper.doesActivityExists(context, vivoIntent)) {
            Log.d("WhiteIntent", "可通过com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity跳转自启动设置")
            sIntentWrapperList.add(WhiteIntentWrapper(vivoIntent, VIVO))
        } else {
            Log.e("WhiteIntent", "不可通过com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity跳转自启动设置")
            vivoIntent = Intent()
            vivoIntent.component = ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.PurviewTabActivity")
            vivoIntent.putExtra("packageName", context.packageName)
            vivoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Log.d("WhiteIntent", "尝试通过com.iqoo.secure.safeguard.PurviewTabActivity跳转自启动设置")
            if (WhiteIntentWrapper.doesActivityExists(context, vivoIntent)) {
                Log.d("WhiteIntent", "可通过com.iqoo.secure.safeguard.PurviewTabActivity跳转自启动设置")
                sIntentWrapperList.add(WhiteIntentWrapper(vivoIntent, VIVO))
            } else {
                Log.e("WhiteIntent", "不可通过com.iqoo.secure.safeguard.PurviewTabActivity跳转自启动设置")
                vivoIntent = Intent()
                vivoIntent.component = ComponentName.unflattenFromString("com.android.settings/com.vivo.settings.DevelpmentSettingsActivity2")
                vivoIntent.putExtra("packageName", context.packageName)
                vivoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                Log.d("WhiteIntent", "尝试通过com.vivo.settings.DevelpmentSettingsActivity2跳转自启动设置")
                if (WhiteIntentWrapper.doesActivityExists(context, vivoIntent)) {
                    Log.d("WhiteIntent", "可通过com.vivo.settings.DevelpmentSettingsActivity2跳转自启动设置")
                    sIntentWrapperList.add(WhiteIntentWrapper(vivoIntent, VIVO))
                } else {
                    Log.e("WhiteIntent", "不可通过com.vivo.settings.DevelpmentSettingsActivity2跳转自启动设置")
                }
            }
        }

        val vivoGodIntent = Intent()
        vivoGodIntent.component = ComponentName("com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity")
        vivoGodIntent.putExtra("packageName", context.packageName)
        vivoGodIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity跳转高耗电设置")
        if (WhiteIntentWrapper.doesActivityExists(context, vivoGodIntent)) {
            Log.d("WhiteIntent", "可通过com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity跳转高耗电设置")
            sIntentWrapperList.add(WhiteIntentWrapper(vivoGodIntent, VIVO_GOD))
        } else {
            Log.e("WhiteIntent", "不可通过com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity跳转高耗电设置")
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
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_vv_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_vv_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
                            .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), DialogInterface.OnClickListener { d, w -> intent.startActivitySafely(a) })
                            .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                            .show()
                    wrapperList.add(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            VIVO_GOD -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_vv_god_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_vv_god_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
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