package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import android.content.Intent
import android.content.ComponentName
import android.util.Log
import android.content.DialogInterface
import com.raqust.bluko.common.wrapper.DialogImpl


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class SonyRom : SystemRom() {

    override val tag = "SonyRom"

    //索尼 自启管理
    private val SONY = 0x120

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        //索尼自启动应用程序管理
        Log.d("WhiteIntent", "索尼手机")
        val sonyIntent = Intent()
        sonyIntent.component = ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity")
        sonyIntent.putExtra("packageName", context.packageName)
        sonyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Log.d("WhiteIntent", "尝试通过com.sonymobile.cta.SomcCTAMainActivity跳转自启动设置")
        if (WhiteIntentWrapper.doesActivityExists(context, sonyIntent)) {
            Log.d("WhiteIntent", "可通过com.sonymobile.cta.SomcCTAMainActivity跳转自启动设置")
            sIntentWrapperList.add(WhiteIntentWrapper(sonyIntent, SONY))
        } else {
            Log.e("WhiteIntent", "不可通过com.sonymobile.cta.SomcCTAMainActivity跳转自启动设置")
        }
    }

    override fun showDialog(reason:String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason,a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            SONY -> {
                DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_sony_title", applicationName),
                        WhiteIntentWrapper.getString(a, "reason_sony_content", reason, applicationName,applicationName),
                        WhiteIntentWrapper.getString(a, "ok"),
                        WhiteIntentWrapper.getString(a, "cancel"), {
                    intent.startActivitySafely(a)
                })
                wrapperList.add(intent)
            }
        }
    }
}