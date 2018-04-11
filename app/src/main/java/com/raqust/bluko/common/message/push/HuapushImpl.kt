package com.raqust.bluko.common.message.push

import android.app.Application
import android.util.Log
import com.huawei.android.hms.agent.HMSAgent


/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: 华为推送的实现
 */
class HuapushImpl() : IPushProvider {
    var huaweiToken = 0

    override fun init(application: Application) {
        HMSAgent.init(application)
//        HMSAgent.connect(application, ConnectHandler { rst -> showLog("HMS connect end:" + rst) })
        HMSAgent.Push.getToken {
            huaweiToken = it
            Log.i("linzehao", "hua pust  " + huaweiToken.toString())
        }
    }

    override fun getPushId(application: Application) {

    }

    override fun stopPush(application: Application) {
        HMSAgent.Push.deleteToken(huaweiToken.toString()) {
            //todo 停止推送
        }
    }

    override fun resumePush(application: Application) {
    }

}