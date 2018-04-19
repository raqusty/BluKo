package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.content.Context
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class ZTERom : IRom {

    val tag = "ZTERom"


    override  fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
    }

    override fun showDilog(a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
    }
}