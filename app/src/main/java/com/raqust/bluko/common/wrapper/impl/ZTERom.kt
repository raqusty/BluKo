package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.content.Context
import com.raqust.bluko.common.wrapper.DialogImpl
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class ZTERom : SystemRom() {

    override val tag = "ZTERom"

    //中兴 自启管理
    private val ZTE = 0x110
    //中兴 锁屏加速受保护应用
    private val ZTE_GOD = 0x111

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>,commandList:List<String>) {
        super.getIntent(context, sIntentWrapperList,commandList)
    }

    override fun showDialog(reason: String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason, a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            ZTE -> {
                DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_zte_title", applicationName),
                        WhiteIntentWrapper.getString(a, "reason_zte_content", reason, applicationName, applicationName),
                        WhiteIntentWrapper.getString(a, "ok"),
                        WhiteIntentWrapper.getString(a, "cancel"), {
                    intent.startActivitySafely(a)
                })
                wrapperList.add(intent)
            }
            ZTE_GOD -> {
                DialogImpl(a, WhiteIntentWrapper.getString(a, "reason_le_god_title", applicationName),
                        WhiteIntentWrapper.getString(a, "reason_le_god_content", reason, applicationName, applicationName),
                        WhiteIntentWrapper.getString(a, "ok"),
                        WhiteIntentWrapper.getString(a, "cancel"), {
                    intent.startActivitySafely(a)
                })
                wrapperList.add(intent)
            }
        }
    }
}