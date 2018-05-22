package com.raqust.bluko.common.message.push

import android.app.Application

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: 小米推送的实现
 */
class MipushImpl : IPushProvider {

    // user your appid the key.
    private val APP_ID = "2882303761517764097"
    // user your appid the key.
    private val APP_KEY = "5151776496097"
    
    override fun init(application: Application) {
    }

    override fun getPushId(application: Application) {
    }

    override fun stopPush(application: Application) {
    }

    override fun resumePush(application: Application) {
    }

}