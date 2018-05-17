package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.raqust.bluko.common.wrapper.DialogImpl
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class GioneeRom : SystemRom() {

    override val tag = "GioneeRom"

    //金立 应用自启
    private val GIONEE = 0x80

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        //金立 应用自启
        Log.d("WhiteIntent", "金立手机")
        val gioneeIntent = Intent()
        gioneeIntent.component = ComponentName("com.gionee.softmanager", "com.gionee.softmanager.MainActivity")
        gioneeIntent.putExtra("packageName", context.packageName)
        gioneeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.gionee.softmanager.MainActivity跳转自启动设置")
        if (WhiteIntentWrapper.doesActivityExists(context, gioneeIntent)) {
            Log.d("WhiteIntent", "可通过com.gionee.softmanager.MainActivity跳转自启动设置")
            sIntentWrapperList.add(WhiteIntentWrapper(gioneeIntent, GIONEE))
        } else {
            Log.e("WhiteIntent", "不可通过com.gionee.softmanager.MainActivity跳转自启动设置")
        }
    }

    override fun showDialog(reason: String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason, a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            GIONEE -> {
                DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_jl_title", applicationName),
                        WhiteIntentWrapper.getString(a, "reason_jl_content", reason, applicationName, applicationName, applicationName),
                        WhiteIntentWrapper.getString(a, "ok"),
                        WhiteIntentWrapper.getString(a, "cancel"), {
                    intent.startActivitySafely(a)
                })
                wrapperList.add(intent)
            }
        }
    }
}