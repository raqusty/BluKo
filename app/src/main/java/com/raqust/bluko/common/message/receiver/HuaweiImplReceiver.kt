package com.raqust.bluko.common.message.receiver

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.huawei.hms.support.api.push.PushReceiver


/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: http://developer.huawei.com/consumer/cn/wiki/index.php?title=HMS%E5%BC%80%E5%8F%91%E6%8C%87%E5%AF%BC%E4%B9%A6-PUSH%E6%9C%8D%E5%8A%A1%E6%8E%A5%E5%8F%A3#6.2_.E8.B0.83.E7.94.A8.E8.AE.BE.E7.BD.AE.E6.98.AF.E5.90.A6.E6.8E.A5.E6.94.B6PUSH.E9.80.8F.E4.BC.A0.E6.B6.88.E6.81.AF.E6.8E.A5.E5.8F.A3
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