package com.raqust.bluko.common.wrapper

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.raqust.bluko.common.utils.RomUtils
import com.raqust.bluko.common.wrapper.impl.*


/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class WhiteIntentWrapper {

    val tag = "WhiteIntent"

     var intent: Intent? = null
     var type: Int = 0

    constructor(intent: Intent, type: Int) {
        this.intent = intent
        this.type = type
    }

    /**
     * 判断本机上是否有能处理当前Intent的Activity
     */
    private fun doesActivityExists(context: Context): Boolean {
        val pm = context.packageManager
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

        private var phoneRom: IRom? = null

        private val sIntentWrapperList by lazy { mutableListOf<WhiteIntentWrapper>() }

        fun doesActivityExists(context: Context, intent: Intent): Boolean {
            val pm = context.packageManager
            val list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            return list != null && list.size > 0
        }

        private fun getIntentWrapperList(context: Context): List<WhiteIntentWrapper> {
            Log.d("WhiteIntent", "Build.VERSION.SDK_INT == " + Build.VERSION.SDK_INT);
            Log.d("WhiteIntent", "Build.MANUFACTURER == " + Build.MANUFACTURER);
            Log.d("WhiteIntent", "Build.BRAND == " + Build.BRAND);

            when {
                RomUtils.isEmui -> phoneRom = EmuiRom()
                RomUtils.isMiui -> phoneRom = MiuiRom()
                RomUtils.isVivo -> phoneRom = VivoRom()
                RomUtils.isOppo -> phoneRom = OppoRom()
                RomUtils.isLeTv -> phoneRom = LetvRom()
                RomUtils.isFlyme -> phoneRom = FlymeRom()
                RomUtils.isSamsung -> phoneRom = SamsungRom()
                RomUtils.isLenovo -> phoneRom = LenovoRom()
                RomUtils.isZTE -> phoneRom = ZTERom()
                RomUtils.isGionee -> phoneRom = GioneeRom()
                RomUtils.isKupai -> phoneRom = KupaiRom()
                RomUtils.isSony -> phoneRom = SonyRom()
                RomUtils.isLG -> phoneRom = LGRom()
                RomUtils.isHTC -> phoneRom = HTCRom()
                else -> {
                    phoneRom = OtherRom()
                }
            }
            phoneRom?.getIntent(context, sIntentWrapperList)
            return sIntentWrapperList
        }

        fun whiteListMatters(activity: Activity?, reason: String): List<WhiteIntentWrapper> {
            val showed by lazy { mutableListOf<WhiteIntentWrapper>() }
            if (activity == null) {
                return showed
            }
            val intentWrapperList = getIntentWrapperList(activity)
            intentWrapperList.forEach {
                phoneRom?.showDilog(reason,activity, it, showed)
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