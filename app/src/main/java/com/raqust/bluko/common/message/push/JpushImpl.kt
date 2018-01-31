package com.raqust.bluko.common.message.push

import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.raqust.bluko.common.utils.PhoneUtil

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: 极光推送的实现类
 */
class JpushImpl : IPushProvider {

    override fun init(application: Application) {
        //如果是debug 就 设置
        if (PhoneUtil.isApkInDebug(application)) JPushInterface.setDebugMode(true)
        JPushInterface.init(application)
    }

    override fun getPushId(application: Application) {
        JPushInterface.getRegistrationID(application)
    }

    override fun stopPush(application: Application) {
        JPushInterface.stopPush(application)
    }

    override fun resumePush(application: Application) {
        JPushInterface.resumePush(application)
    }

}