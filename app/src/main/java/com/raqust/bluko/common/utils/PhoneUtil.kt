package com.raqust.bluko.common.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import android.net.NetworkInfo



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

     fun isWifi(mContext: Context): Boolean {
        val connectivityManager = mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
    }
}
