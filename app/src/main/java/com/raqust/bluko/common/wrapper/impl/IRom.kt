package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.content.Context
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper

/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
interface IRom{
    fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>)

    fun showDilog(reason:String,a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>)
}