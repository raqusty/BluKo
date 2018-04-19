package com.raqust.bluko.common.wrapper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.raqust.bluko.common.wrapper.impl.SystemRom
import android.annotation.TargetApi
import com.raqust.bluko.common.wrapper.impl.IRom
import com.raqust.bluko.common.wrapper.impl.MiuiRom


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
public final class WhiteIntentWrapper {

    val tag = "WhiteIntent"

    private var intent: Intent? = null
    private var type: Int = 0

    constructor(intent: Intent, type: Int) {
        this.intent = intent
        this.type = type
    }

    /**
     * 判断本机上是否有能处理当前Intent的Activity
     */
    private fun doesActivityExists(context: Context): Boolean {
        val pm = context.getPackageManager()
        val list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list != null && list!!.size > 0
    }

    /**
     * 安全地启动一个Activity
     */
     fun startActivitySafely(activityContext: Activity) {
        try {
            activityContext.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        //Android 7.0+ Doze 模式
        val SYSTEM = 0x00
        val DOZE = 0x01

        var phoneRom :IRom?=null
        var systemRom :SystemRom?=null

        private val sIntentWrapperList by lazy { mutableListOf<WhiteIntentWrapper>() }

        fun doesActivityExists(context: Context, intent: Intent): Boolean {
            val pm = context.packageManager
            val list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            return list != null && list.size > 0
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun getIntentWrapperList(context: Context): List<WhiteIntentWrapper> {
            Log.d("WhiteIntent", "Build.VERSION.SDK_INT == " + Build.VERSION.SDK_INT);
            Log.d("WhiteIntent", "Build.MANUFACTURER == " + Build.MANUFACTURER);
            Log.d("WhiteIntent", "Build.BRAND == " + Build.BRAND);

            //Android 7.0+ Doze 模式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                systemRom = SystemRom()
                systemRom ?.getIntent(context,sIntentWrapperList)
            }

            phoneRom = MiuiRom()
            phoneRom?.getIntent(context, sIntentWrapperList)
//
//            if (RomUtils.isEmui()) {
//
//            }

            return sIntentWrapperList
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun whiteListMatters(activity: Activity?, reason: String): List<WhiteIntentWrapper> {
             val showed by lazy { mutableListOf<WhiteIntentWrapper>() }
            if (activity == null) {
                return showed
            }
            val intentWrapperList = getIntentWrapperList(activity)
            intentWrapperList.forEach {
                when(it.type){
                    SYSTEM,DOZE ->{
                        systemRom?.showDilog(activity,it,showed)
                    }
                    else->{
                        phoneRom?.showDilog(activity,it,showed)
                    }
                }
            }
            return showed
        }



            private var sApplicationName: String? = ""

        fun getApplicationName(context: Context): String {
            if (sApplicationName == null) {
                val pm: PackageManager
                val ai: ApplicationInfo
                try {
                    pm = context.packageManager
                    ai = pm.getApplicationInfo(context.packageName, 0)
                    sApplicationName = pm.getApplicationLabel(ai).toString()
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                    sApplicationName = context.packageName
                }

            }
            return sApplicationName!!
        }

         fun getString(context: Context, name: String, vararg format: Any): String {
            try {
                val resId = context.resources.getIdentifier(name, "string", context.packageName)
                if (resId > 0) {
                    return context.getString(resId, *format)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }
    }
}