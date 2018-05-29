package com.raqust.bluko.module.testActivity

import com.raqust.bluko.common.db.BaseTableFields

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
internal object ChatDbTable:BaseTableFields{
    override val TABLE_NAME: String
        get() = "conversation_info"
    override val PRIMARY_KEY_ID: String
        get() = "_id"

    /**
     * 聊天类型，目前没用
     */
    val CHATERTYPE = "chaterType"

    /**
     * 聊天的用户id
     */
    val CHATERID = "chaterId"

    /**
     * 用户头像
     */
    val CHATERICON = "chaterIcon"

    /**
     * 会话的数据 json
     */
    val CHATERBODY = "chaterBody"

    /**
     * 最后一条消息
     */
    val LASTMSG = "lastMsg"

    /**
     * 最后一条消息的时间
     */
    val TIME = "time"

    /**
     * 拓展字段
     */
    val TAG1 = "tag1"

}