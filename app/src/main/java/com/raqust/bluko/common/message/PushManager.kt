package com.raqust.bluko.common.message

import android.app.Application
import com.raqust.bluko.common.message.push.HuapushImpl
import com.raqust.bluko.common.message.push.IPushProvider
import com.raqust.bluko.common.message.push.JpushImpl

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: 推送的管理类
 */
object PushManager : IPushProvider {


    private val pushImpl: IPushProvider by lazy {
        //todo 这里通过判断是什么机子，来决定用sdk
//        JpushImpl()
        HuapushImpl()
    }

    override fun init(application: Application) {
        pushImpl.init(application)
    }

    override fun getPushId(application: Application) {
        pushImpl.getPushId(application)
    }

    override fun stopPush(application: Application) {
        pushImpl.stopPush(application)
    }

    override fun resumePush(application: Application) {
        pushImpl.resumePush(application)
    }
}