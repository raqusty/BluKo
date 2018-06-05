package com.raqust.bluko.module.testActivity.chat

import com.raqust.bluko.common.db.BaseTableFields

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
internal object ChatDbTable{
    val TABLE_NAME = "conversation_info"

    val PRIMARY_KEY_ID= "_id"

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
     * 用户名字
     */
    val CHATERNAME = "chaterName"

    /**
     * 附件，用来保存图片，视频 json
     */
    val ATTACHMENT = "attachment"

    /**
     * letter id
     */
    val LETTERID = "letterId"

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
     * 未读数
     */
    val UNREADCOUNT = "unReadCount"

    /**
     * 拓展字段
     */
    val TAG1 = "tag1"

}