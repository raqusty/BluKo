package com.raqust.bluko.module.picture

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.view.View
import android.widget.RemoteViews
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.message.MsgConstants

/**
 * Created by linzehao
 * time: 2018/3/12.
 * info:
 */
class PictureActivity : BaseActivity() {


    override fun initViews() {

    }


    override fun setListener() {

    }

    override fun getToolBarResId(): Int {
        return 0
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager) {

    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_login
    }

    var msgid = 1

    fun click(v: View) {
        when (v.id) {
            R.id.text1 -> {
                var msgIntent = Intent(MsgConstants.INTENT_WEBVIEW)
                handleNotification(this, "测试", "我是谁", (msgid++).toString(), msgIntent)
            }
            R.id.text2 -> {

            }
            R.id.text3 -> {
            }
            R.id.text4 -> {

            }
            R.id.text5 -> {
            }
            R.id.text6 -> {
            }
            else -> {
            }
        }
    }


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

}
