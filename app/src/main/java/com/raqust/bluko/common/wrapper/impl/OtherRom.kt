package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import com.raqust.bluko.common.wrapper.Constant
import com.raqust.bluko.common.wrapper.Constant.COMMAND_START_YOURSELF
import com.raqust.bluko.common.wrapper.DialogImpl
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class OtherRom : SystemRom() {

    override val tag = "OtherRom"

    //Other 自启管理
    private val OTHER = 0x150

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>, commandList: List<String>) {
        super.getIntent(context, sIntentWrapperList, commandList)
        (0 until commandList.size ).forEach {
            when (commandList[it]) {
                COMMAND_START_YOURSELF -> {
                    if (sIntentWrapperList.isEmpty()) {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        intent.data = Uri.fromParts("package", context.packageName, null)
                        Log.d("WhiteIntent", "尝试通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页")
                        if (WhiteIntentWrapper.doesActivityExists(context, intent)) {
                            Log.d("WhiteIntent", "可通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页")
                            sIntentWrapperList.add(WhiteIntentWrapper(intent, OTHER,COMMAND_START_YOURSELF))
                        } else {
                            Log.e("WhiteIntent", "不可通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页")
                        }
                    }
                }
            }
        }
    }

    override fun showDialog(reason: String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason, a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            OTHER -> {
                DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_system_title", applicationName),
                        WhiteIntentWrapper.getString(a, "reason_system_content", reason, applicationName, applicationName, applicationName),
                        WhiteIntentWrapper.getString(a, "ok"),
                        WhiteIntentWrapper.getString(a, "cancel"), {
                    intent.startActivitySafely(a)
                })
                wrapperList.add(intent)
            }
        }
    }
}