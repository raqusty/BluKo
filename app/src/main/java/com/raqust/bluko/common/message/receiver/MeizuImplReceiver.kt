package com.raqust.bluko.common.message.receiver

import android.content.Context
import android.util.Log
import com.meizu.cloud.pushsdk.MzPushMessageReceiver
import com.meizu.cloud.pushsdk.handler.MzPushMessage
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder
import com.meizu.cloud.pushsdk.platform.message.*


/**
 * Created by linzehao
 * time: 2018/4/11.
 * info: https://github.com/MEIZUPUSH/PushDemo
 */
class MeizuImplReceiver : MzPushMessageReceiver() {

    val tag = "MeizuImplReceiver"
    override fun onRegister(context: Context, pushid: String) {
        //调用PushManager.register(context）方法后，会在此回调注册状态
        //应用在接受返回的pushid
        Log.i(tag,"pushid  "+pushid)
    }

    override fun onMessage(context: Context?, s: String?) {
        //接收服务器推送的透传消息
        Log.i(tag,"s   "+s)
    }

    override fun onUnRegister(context: Context, b: Boolean) {
        //调用PushManager.unRegister(context）方法后，会在此回调反注册状态
    }

//    //设置通知栏小图标
     override fun onUpdateNotificationBuilder(pushNotificationBuilder: PushNotificationBuilder) {
        //重要,详情参考应用小图标自定设置
//        pushNotificationBuilder.setmStatusbarIcon(R.drawable.mz_push_notification_small_icon)
    }

    override fun onPushStatus(context: Context, pushSwitchStatus: PushSwitchStatus) {
        //检查通知栏和透传消息开关状态回调
    }

    override fun onRegisterStatus(context: Context, registerStatus: RegisterStatus) {
        //调用新版订阅PushManager.register(context,appId,appKey)回调
    }

    override fun onUnRegisterStatus(context: Context, unRegisterStatus: UnRegisterStatus) {
        //新版反订阅回调
    }

    override fun onSubTagsStatus(context: Context, subTagsStatus: SubTagsStatus) {
        //标签回调
    }

    override fun onSubAliasStatus(context: Context, subAliasStatus: SubAliasStatus) {
        //别名回调
    }

    override fun onNotificationArrived(context: Context?, mzPushMessage: MzPushMessage?) {
        //通知栏消息到达回调，flyme6基于android6.0以上不再回调
        Log.i(tag,"mzPushMessage  "+mzPushMessage.toString())
    }

    override fun onNotificationClicked(context: Context?, mzPushMessage: MzPushMessage?) {
        //通知栏消息点击回调
        Log.i(tag,"mzPushMessage  "+mzPushMessage.toString())
    }

    override fun onNotificationDeleted(context: Context?, mzPushMessage: MzPushMessage?) {
        //通知栏消息删除回调；flyme6基于android6.0以上不再回调
        Log.i(tag,"mzPushMessage  "+mzPushMessage.toString())
    }

}
