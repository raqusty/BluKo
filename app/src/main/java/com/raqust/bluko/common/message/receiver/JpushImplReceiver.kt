package com.raqust.bluko.common.message.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.jiguang.share.android.utils.Logger
import cn.jpush.android.api.JPushInterface
import com.raqust.bluko.common.message.MessageHandle

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info:
 */
class JpushImplReceiver : BroadcastReceiver() {
    private var nm: NotificationManager? = null

    private val tag = "JpushImplReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (null == nm) {
            nm = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        if (JPushInterface.ACTION_MESSAGE_RECEIVED == intent?.action) {
            Logger.d(tag, "接受到推送下来的自定义消息")
            val bundle = intent.extras
            val title = bundle.getString(JPushInterface.EXTRA_TITLE)
            val message = bundle.getString(JPushInterface.EXTRA_MESSAGE)
            val extras = bundle.getString(JPushInterface.EXTRA_EXTRA)
            val msgId =  bundle.getString(JPushInterface.EXTRA_MSG_ID)
            if (context!=null)
                MessageHandle.handleMessage(context,title, message,msgId, extras)
        }

    }
}