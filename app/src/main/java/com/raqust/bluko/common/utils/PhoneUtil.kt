package com.raqust.bluko.common.utils

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info:手机相关的工具
 */

object PhoneUtil {

    /**
     * 判断apk是不是debug
     */
    fun isApkInDebug(context: Context): Boolean {
        try {
            val info = context.applicationInfo
            return info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: Exception) {

        }

        return false
    }
}
