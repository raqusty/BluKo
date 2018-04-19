package com.raqust.bluko.common.utils

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
}