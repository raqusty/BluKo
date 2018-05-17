package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.content.Context
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class HTCRom : SystemRom() {

    override val tag = "HTCRom"


    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>,commandList:List<String>) {
        super.getIntent(context, sIntentWrapperList,commandList)
    }

    override fun showDialog(reason: String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason, a, intent, wrapperList)
        val applicationName = WhiteIntentWrapper.getApplicationName(a)
        when (intent.type) {
            DOZE -> {

            }
        }
    }
}