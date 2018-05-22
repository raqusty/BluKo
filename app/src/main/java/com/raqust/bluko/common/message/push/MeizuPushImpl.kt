package com.raqust.bluko.common.message.push

import android.app.Application

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: 魅族推送的实现
 */
class MeizuPushImpl : IPushProvider {

    private val APP_ID = "112985"
    private val APP_KEY = "bd96751debf84f698f303d946caeb47d"

    override fun init(application: Application) {
    }

    override fun getPushId(application: Application) {

    }

    override fun stopPush(application: Application) {
    }

    override fun resumePush(application: Application) {
    }

}