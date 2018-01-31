package com.raqust.bluko.common.message.push

import android.app.Application

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info：push 推送的接口
 */
interface IPushProvider{

    /**
     * 初始化sdk
     */
    fun init(application: Application)


    /**
     * 获取pushId
     */
    fun getPushId(application: Application)

    /**
     * 停止推送
     */
    fun stopPush(application: Application)

    /**
     * 从新激活推送
     */
    fun resumePush(application: Application)
}