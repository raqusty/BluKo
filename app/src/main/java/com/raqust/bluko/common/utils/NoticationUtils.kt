package com.raqust.bluko.common.utils

import android.app.AppOpsManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import com.raqust.bluko.R
import java.lang.reflect.InvocationTargetException


/**
 * Created by linzehao
 * time: 2018/4/18.
 * info:
 */
object NotificationsUtils {

    private val CHECK_OP_NO_THROW = "checkOpNoThrow"
    private val OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION"

    fun isNotificationEnabled(context: Context): Boolean {

        val mAppOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

        val appInfo = context.getApplicationInfo()

        val pkg = context.getApplicationContext().getPackageName()

        val uid = appInfo.uid

        var appOpsClass: Class<*>? = null /* Context.APP_OPS_MANAGER */

        try {

            appOpsClass = Class.forName(AppOpsManager::class.java.name)

            val checkOpNoThrowMethod = appOpsClass!!.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String::class.java)

            val opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION)
            val value = opPostNotificationValue.get(Int::class.java) as Int

            return checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) as Int == AppOpsManager.MODE_ALLOWED

        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return false
    }

    fun goToSetNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
            // 进入设置系统应用权限界面
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(intent)
            return
        } else {
            val intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }
    }

    /**
     产生消息
     **/
     fun handleNotification(context: Context, title: String, message: String, msgId: String, msgIntent: Intent) {
        val notifyManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context)
        //http://blog.csdn.net/moguivstianshi/article/details/52368175
        val pendingIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), msgIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setDefaults(NotificationCompat.DEFAULT_ALL)
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
        notifyManager.notify(msgId, 0, builder.build())
    }
}