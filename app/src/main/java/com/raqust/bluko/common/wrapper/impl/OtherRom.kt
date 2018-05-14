package com.raqust.bluko.common.wrapper.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import android.content.DialogInterface



/**
 * Created by linzehao
 * time: 2018/4/17.
 * info:
 */
class OtherRom : SystemRom() {

    override   val tag = "ZTERom"

    //Other 自启管理
    private val OTHER = 0x150

    override fun getIntent(context: Context, sIntentWrapperList: MutableList<WhiteIntentWrapper>) {
        super.getIntent(context, sIntentWrapperList)
        if (sIntentWrapperList.isEmpty()) {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.fromParts("package", context.packageName, null)
            Log.d("WhiteIntent", "尝试通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页")
            if (WhiteIntentWrapper.doesActivityExists(context, intent)) {
                Log.d("WhiteIntent", "可通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页")
                sIntentWrapperList.add(WhiteIntentWrapper(intent, OTHER))
            } else {
                Log.e("WhiteIntent", "不可通过android.settings.APPLICATION_DETAILS_SETTINGS Action跳转应用详情页")
            }
        }
    }

    override fun showDialog(reason:String, a: Activity, intent: WhiteIntentWrapper, wrapperList: MutableList<WhiteIntentWrapper>) {
        super.showDialog(reason,a, intent, wrapperList)
        when (intent.type) {
            OTHER -> {
                try {
                    AlertDialog.Builder(a)
                            .setCancelable(false)
                            .setTitle(WhiteIntentWrapper.getString(a, "reason_system_title",WhiteIntentWrapper.getApplicationName(a)))
                            .setMessage(WhiteIntentWrapper.getString(a, "reason_system_content", reason,WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a),WhiteIntentWrapper.getApplicationName(a)))
                            .setPositiveButton(WhiteIntentWrapper.getString(a, "ok"), DialogInterface.OnClickListener { d, w -> intent.startActivitySafely(a) })
                            .setNegativeButton(WhiteIntentWrapper.getString(a, "cancel"), null)
                            .show()
                    wrapperList.add(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}