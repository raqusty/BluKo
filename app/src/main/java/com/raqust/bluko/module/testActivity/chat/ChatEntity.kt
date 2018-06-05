package com.raqust.bluko.module.testActivity.chat

import com.google.gson.Gson

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
data class ChatList(val list: List<ChatEntity>)

/**
 * 网络的数据结构
 */
data class ChatEntity(
        val content: String,//文本内容 ,
        val gmtCreate: Long,//发送时间戳
        val attachment : String, //附件 ,
        val letterId :String,//私信ID 用来判断是否是最新的
        val letterScene: String,//私信场景：1=>用户发的（默认），2=>运营发的 ,
        val letterType: String,//私信类型：1=>文本（默认），2=>图片 ,
        val nonReadCount: Int,//总未读数 ,
        val receiver: UserInfo?,//接收方 ,   （目标）
        val sender: UserInfo?)//发送方  (我的)

data class UserInfo(val userId: Long, val avatar: String, val nickname: String, val userGroupType: Int)


data class ChatInfoList(val list: List<ChatInfoEntity>)

/**
 * 存数据库结构
 */
class ChatInfoEntity(val map: MutableMap<String, Any?>) {
    val _id: Long by map

    var chaterType: String by map
    var chaterId: String by map
    var chaterName: String by map
    var chaterIcon: String by map
    var chaterBody: String by map
    var lastMsg: String by map
    var attachment: String by map
    var letterId: String by map
    var time: Long by map
    var unReadCount: Int by map
    var tag1: String by map

    constructor(chaterType: String, chaterId: String,chaterName: String, chaterIcon: String, chaterBody: String, lastMsg: String,attachment: String, time: Long, unReadCount: Int,letterId: String,
                tag1: String) : this(HashMap()) {
        this.chaterType = chaterType
        this.chaterId = chaterId
        this.chaterName = chaterName
        this.chaterIcon = chaterIcon
        this.chaterBody = chaterBody
        this.lastMsg = lastMsg
        this.attachment = attachment
        this.letterId = letterId
        this.time = time
        this.unReadCount = unReadCount
        this.tag1 = tag1
    }

    /**
     *  ChatEntity -> ChatInfoEntity
     *
     *  chaterId =sender(我的)-receiver（目标）
     *  但有可能 反过来
     */
    constructor(entity: ChatEntity) : this(HashMap()) {
        this.chaterType = entity.letterType
        this.chaterId = (entity.sender?.userId ?: 0L).toString() + "-" + entity.receiver?.userId
        this.chaterIcon = entity.receiver?.avatar ?: ""
        this.chaterName = entity.receiver?.nickname ?: ""
        this.chaterBody = Gson().toJson(entity)
        this.lastMsg = entity.content
        this.attachment = entity.attachment
        this.letterId =entity. letterId
        this.time = entity.gmtCreate
        this.unReadCount = entity.nonReadCount
        this.tag1 = ""
    }
}