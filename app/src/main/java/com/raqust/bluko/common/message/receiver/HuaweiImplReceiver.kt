package com.raqust.bluko.common.message.receiver

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.huawei.hms.support.api.push.PushReceiver


/**
 * Created by linzehao
 * time: 2018/1/31.
 * info:
 */
class HuaweiImplReceiver : PushReceiver() {
    private var nm: NotificationManager? = null

    private val tag = "hwaiImplReceiver"
    override fun onToken(context: Context, token: String, extras: Bundle?) {
        Log.i(tag,token)
    }
    override fun onPushMsg(context: Context, msg: ByteArray, bundle: Bundle): Boolean {
        Log.i(tag,"onPushState  "+msg.toString())
        return true
    }
    override fun onEvent(context: Context?, event: PushReceiver.Event?, extras: Bundle?) {
    }
    override fun onPushState(context: Context?, pushState: Boolean) {
        Log.i(tag,"onPushState  "+pushState)
    }
}