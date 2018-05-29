package com.raqust.bluko.module.testActivity

import com.google.gson.Gson
import org.json.JSONObject

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
        val content: String,
        val gmtCreate: Long,
        val imageUrl: String,
        val letterScene: String,
        val letterType: String,
        val nonReadCount: String,
        val receiver: UserInfo?,
        val sender: UserInfo?)

data class UserInfo(val userId: Long, val avatar: String, val nickname: String, val userGroupType: Int)


data class ChatInfoList(val list: List<ChatInfoEntity>)

/**
 * 存数据库结构
 */
class ChatInfoEntity(val map: MutableMap<String, Any?>) {
    val _id: Long by map

    var chaterType: String by map
    var chaterId: String by map
    var chaterIcon: String by map
    var chaterBody: String by map
    var lastMsg: String by map
    var time: Long by map
    var tag1: String by map

    constructor(chaterType: String, chaterId: String, chaterIcon: String, chaterBody: String, lastMsg: String, time: Long,
                tag1: String) : this(HashMap()) {
        this.chaterType = chaterType
        this.chaterId = chaterId
        this.chaterIcon = chaterIcon
        this.chaterBody = chaterBody
        this.lastMsg = lastMsg
        this.time = time
        this.tag1 = tag1
    }

    /**
     *  ChatEntity -> ChatInfoEntity
     *
     *  chaterId =sender-receiver
     *  但有可能 反过来
     */
    constructor(entity: ChatEntity) : this(HashMap()) {
        this.chaterType = entity.letterType
        this.chaterId = (entity.sender?.userId ?: 0L).toString() + "-" + entity.receiver?.userId
        this.chaterIcon = entity.receiver?.avatar ?: ""
        this.chaterBody = Gson().toJson(entity)
        this.lastMsg = entity.content
        this.time = entity.gmtCreate
        this.tag1 = ""
    }
}