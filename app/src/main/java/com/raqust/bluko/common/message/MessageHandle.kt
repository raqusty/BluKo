package com.raqust.bluko.common.message

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.text.TextUtils
import android.util.Log
import android.widget.RemoteViews
import com.google.gson.Gson
import com.raqust.bluko.R
import com.raqust.bluko.common.event.DataSynEvent
import com.raqust.bluko.common.message.entity.MessageEntity
import org.json.JSONObject
import com.raqust.bluko.common.event.MessageEvent
import com.raqust.bluko.common.utils.AppUtil
import com.zhy.http.okhttp.OkHttpUtils.post
import org.greenrobot.eventbus.EventBus


/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: 消息分发
 */
object MessageHandle {

    private val tag = "MessageHandle"

    /**
     * 开始分发消息
     * title 标题
     * message 内容
     * extrasJson 收到的json数据
     */
    fun handleMessage(context: Context, title: String, message: String, msgId: String, extrasJson: String) {
        if (TextUtils.isEmpty(extrasJson)) {
            Log.e(tag, "extrasJson is null")
            return
        }

        val msgEntity = getMsgEntity(extrasJson)
        when(msgEntity.notify){
            0 ->{//0 ：隐藏通知栏
                if (AppUtil.isBackground1()){
                    EventBus.getDefault().post(MessageEvent(3,msgEntity.msgCode))
                }else{
                    EventBus.getDefault().post(MessageEvent(4,msgEntity.msgCode))
                }
            }
            1 ->{//1 ：显示通知栏
                var msgIntent = handleIntent(msgEntity.msgCode)
                if (msgIntent == null) {
                    Log.e(tag, "intent is null")
                    return
                }
                msgIntent = getMsgParams(msgIntent, msgEntity.params)
                handleNotification(context, title, message, msgId, msgIntent)


            }
            2 ->{//2：可显示可不显示
                if (AppUtil.isBackground(context)){
                    EventBus.getDefault().post(MessageEvent(1,msgEntity.msgCode))
                }else{
                    EventBus.getDefault().post(MessageEvent(2,msgEntity.msgCode))
                }
            }

        }

    }

    /**
     * 解析数据并获取数据
     */
    private fun getMsgEntity(jsonString: String): MessageEntity {
        return Gson().fromJson(jsonString, MessageEntity::class.java)
    }

    /**
     * 获取Params的数据
     */
    private fun getMsgParams(msgIntent: Intent, jsonString: String): Intent {
        if (!TextUtils.isEmpty(jsonString)) {
            val json = JSONObject(jsonString)
            json.keys().forEach {
                val value = json[it]
                if (value is Int) {
                    msgIntent.putExtra(it, value)
                } else if (value is String) {
                    msgIntent.putExtra(it, value)
                }

            }
        }
        return msgIntent
    }

    /**
     * 分发通知
     * 通知 ：因为16以下无法改变通知栏大小，所以小于16用默认的
     * https://stackoverflow.com/questions/21237495/create-custom-big-notifications
     */
    private fun handleNotification(context: Context, title: String, message: String, msgId: String, msgIntent: Intent) {
        val notifyManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context)
        val pendingIntent = PendingIntent.getActivity(context, 0, msgIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //如果是大于16的，就用自定义的类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val remoteViews = RemoteViews(context.packageName, R.layout.layout_notification)
            remoteViews.setTextViewText(R.id.layout_app, context.packageName)
            remoteViews.setTextViewText(R.id.layout_text, title)
            remoteViews.setImageViewResource(R.id.layout_image, R.mipmap.ic_launcher_round)
            remoteViews.setTextViewText(R.id.layout_text1, message)
            builder.setCustomBigContentView(remoteViews)
        }
        builder.setDefaults(NotificationCompat.DEFAULT_ALL)
//        builder.setFullScreenIntent(pendingIntent, true);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
        notifyManager.notify(msgId, 0, builder.build())
    }

    /**
     * 跳转的intent
     */
    private fun handleIntent(msgCode: String): Intent? {
        var intent: Intent? = null
        when (msgCode) {
            MsgConstants.MSG_WEBVIEW -> {
                intent = Intent(MsgConstants.INTENT_WEBVIEW)
            }
            MsgConstants.MSG_NOTIFY -> {
                intent = Intent(MsgConstants.INTENT_MESSAGE_DETAIL)
            }
            MsgConstants.MSG_AT_FIRST_COMMENT -> {
                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
            MsgConstants.MSG_AT_SECOND_COMMENT -> {
                intent = Intent(MsgConstants.INTENT_COMMENT_DETAIL)
            }
            MsgConstants.MSG_AT_CONTENT -> {
                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
            MsgConstants.MSG_PRAISE_FIRST_COMMENT -> {
                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
            MsgConstants.MSG_PRAISE_SECOND_COMMENT -> {
                intent = Intent(MsgConstants.INTENT_COMMENT_DETAIL)
            }
            MsgConstants.MSG_PRAISE_CONTENT -> {
                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
            MsgConstants.MSG_FIRST_COMMENT -> {
                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
            MsgConstants.MSG_FORWARD_CONTENT -> {
                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
            MsgConstants.MSG_SECOND_COMMENT -> {
                intent = Intent(MsgConstants.INTENT_COMMENT_DETAIL)
            }
            MsgConstants.MSG_WATCH -> {
                //Todo  暂时没有
//                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
            MsgConstants.MSG_SECRET_LETTER -> {
                //Todo  暂时没有
//                intent = Intent(MsgConstants.INTENT_HOME_DETAIL)
            }
        }
        return intent
    }


}