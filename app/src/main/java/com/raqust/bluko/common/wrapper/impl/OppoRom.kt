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
class OppoRom : SystemRom() {

    override val tag = "OppoRom"

    //Oppo 自启动管理
    private val OPPO_PM = 0x50//权限管理
    private val OPPO_SM = 0x51//自启动管理
    private val OPPO_GOD = 0x52

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        //小米 自启动管理
        //Oppo 自启动管理
        Log.d("WhiteIntent", "Oppo手机")
        var oppoIntent = Intent()
        oppoIntent.component = ComponentName.unflattenFromString("com.coloros.safecenter/com.coloros.privacypermissionsentry.PermissionTopActivity")
        oppoIntent.putExtra("packageName", context.packageName)
        oppoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.coloros.privacypermissionsentry.PermissionTopActivity跳转权限隐私设置")
        if (WhiteIntentWrapper.doesActivityExists(context, oppoIntent)) {
            Log.d("WhiteIntent", "可通过com.coloros.privacypermissionsentry.PermissionTopActivity跳转权限隐私设置")
            sIntentWrapperList.add(WhiteIntentWrapper(oppoIntent, OPPO_PM))
        } else {
            Log.e("WhiteIntent", "不可通过com.coloros.privacypermissionsentry.PermissionTopActivity跳转权限隐私设置")
            oppoIntent = Intent()
            oppoIntent.component = ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")
            oppoIntent.putExtra("packageName", context.packageName)
            oppoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Log.d("WhiteIntent", "尝试通过com.coloros.safecenter.permission.startup.StartupAppListActivity跳转自启动设置")
            if (WhiteIntentWrapper.doesActivityExists(context, oppoIntent)) {
                Log.d("WhiteIntent", "可通过com.coloros.safecenter.permission.startup.StartupAppListActivity跳转自启动设置")
                sIntentWrapperList.add(WhiteIntentWrapper(oppoIntent, OPPO_SM))
            } else {
                Log.e("WhiteIntent", "不可通过com.coloros.safecenter.permission.startup.StartupAppListActivity跳转自启动设置")
                oppoIntent = Intent()
                oppoIntent.component = ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity")
                oppoIntent.putExtra("packageName", context.packageName)
                oppoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                Log.d("WhiteIntent", "尝试通过com.color.safecenter.permission.startup.StartupAppListActivity跳转自启动设置")
                if (WhiteIntentWrapper.doesActivityExists(context, oppoIntent)) {
                    Log.d("WhiteIntent", "可通过com.color.safecenter.permission.startup.StartupAppListActivity跳转自启动设置")
                    sIntentWrapperList.add(WhiteIntentWrapper(oppoIntent, OPPO_SM))
                } else {
                    Log.e("WhiteIntent", "不可通过com.color.safecenter.permission.startup.StartupAppListActivity跳转自启动设置")
                    oppoIntent = Intent()
                    oppoIntent.component = ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity")
                    oppoIntent.putExtra("packageName", context.packageName)
                    oppoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    Log.d("WhiteIntent", "尝试通过com.color.safecenter.permission.PermissionManagerActivity跳转自启动设置")
                    if (WhiteIntentWrapper.doesActivityExists(context, oppoIntent)) {
                        Log.d("WhiteIntent", "可通过com.color.safecenter.permission.PermissionManagerActivity跳转自启动设置")
                        sIntentWrapperList.add(WhiteIntentWrapper(oppoIntent, OPPO_PM))
                    } else {
                        Log.e("WhiteIntent", "不可通过com.color.safecenter.permission.PermissionManagerActivity跳转自启动设置")
                        oppoIntent = Intent()
                        oppoIntent.component = ComponentName.unflattenFromString("com.oppo.safe/.permission.startup.StartupAppListActivity")
                        oppoIntent.putExtra("packageName", context.packageName)
                        oppoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        Log.d("WhiteIntent", "尝试通过com.oppo.safe.permission.startup.StartupAppListActivity跳转自启动设置")
                        if (WhiteIntentWrapper.doesActivityExists(context, oppoIntent)) {
                            Log.d("WhiteIntent", "可通过com.oppo.safe.permission.startup.StartupAppListActivity跳转自启动设置")
                            sIntentWrapperList.add(WhiteIntentWrapper(oppoIntent, OPPO_SM))
                        } else {
                            Log.e("WhiteIntent", "不可通过com.oppo.safe.permission.startup.StartupAppListActivity跳转自启动设置")
                        }
                    }
                }
            }
        }
        //OPPO 纯净后台
        var oppoGodIntent = Intent()
        oppoGodIntent.component = ComponentName.unflattenFromString("com.oppo.purebackground/.PurebackgroundTopActivity")
        oppoGodIntent.putExtra("packageName", context.packageName)
        oppoGodIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.oppo.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
        if (WhiteIntentWrapper.doesActivityExists(context, oppoGodIntent)) {
            Log.d("WhiteIntent", "可通过com.oppo.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
            sIntentWrapperList.add(WhiteIntentWrapper(oppoGodIntent, OPPO_GOD))
        } else {
            Log.e("WhiteIntent", "不可通过com.oppo.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
            oppoGodIntent = Intent()
            oppoGodIntent.component = ComponentName.unflattenFromString("com.color.purebackground.PurebackgroundTopActivity")
            oppoGodIntent.putExtra("packageName", context.packageName)
            oppoGodIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Log.d("WhiteIntent", "尝试通过com.color.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
            if (WhiteIntentWrapper.doesActivityExists(context, oppoGodIntent)) {
                Log.d("WhiteIntent", "可通过com.color.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
                sIntentWrapperList.add(WhiteIntentWrapper(oppoGodIntent, OPPO_GOD))
            } else {
                Log.e("WhiteIntent", "不可通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
                oppoGodIntent = Intent()
                oppoGodIntent.component = ComponentName.unflattenFromString("com.coloros.purebackground.PurebackgroundTopActivity")
                oppoGodIntent.putExtra("packageName", context.packageName)
                oppoGodIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                Log.d("WhiteIntent", "尝试通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
                if (WhiteIntentWrapper.doesActivityExists(context, oppoGodIntent)) {
                    Log.d("WhiteIntent", "可通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
                    sIntentWrapperList.add(WhiteIntentWrapper(oppoGodIntent, OPPO_GOD))
                } else {
                    Log.e("WhiteIntent", "不可通过com.coloros.purebackground.PurebackgroundTopActivity跳转纯净后台设置页")
                }
            }
        }
    }
    override fun showDialog(reason:String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason,a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            OPPO_SM -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_oppo_sm_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_oppo_sm_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
                            .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), DialogInterface.OnClickListener { d, w -> intent.startActivitySafely(a) })
                            .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                            .show()
                    wrapperList.add(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            OPPO_PM -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_oppo_pm_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_oppo_pm_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
                            .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), DialogInterface.OnClickListener { d, w -> intent.startActivitySafely(a) })
                            .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                            .show()
                    wrapperList.add(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            OPPO_GOD -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_oppo_god_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_oppo_god_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
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