package com.raqust.bluko.common.message.entity

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info:消息的实体类
 */


data class MessageEntity(
        val notify: Int = 0, // 0 ：隐藏通知栏  1 ：显示通知栏  2：可显示可不显示
        val badge: Int = 0,  //  Android 不传
        val msgCode: String = "",//MSG_AT_FIRST_COMMENT
        val params: String = "" // json
)