package com.raqust.bluko.common.message

/**
 * Created by linzehao
 * time: 2018/1/31.
 * info: msgCode常量
 */
object MsgConstants {
    val MSG_WEBVIEW = "MSG_WEBVIEW"  //运营消息
    val MSG_NOTIFY = "MSG_NOTIFY"   // 系统消息
    val MSG_AT_FIRST_COMMENT = "MSG_AT_FIRST_COMMENT" //一级评论@
    val MSG_AT_SECOND_COMMENT = "MSG_AT_SECOND_COMMENT"//二级评论@
    val MSG_AT_CONTENT = " MSG_AT_CONTENT"//内容中@
    val MSG_PRAISE_FIRST_COMMENT = "MSG_PRAISE_FIRST_COMMENT"// 一级评论点赞
    val MSG_PRAISE_SECOND_COMMENT = "MSG_PRAISE_SECOND_COMMENT"// 二级评论点赞
    val MSG_PRAISE_CONTENT = "MSG_PRAISE_CONTENT"//内容中点赞
    val MSG_FORWARD_CONTENT = " MSG_FORWARD_CONTENT"// 转发内容
    val MSG_FIRST_COMMENT = " MSG_FIRST_COMMENT"//一级评论
    val MSG_SECOND_COMMENT = "MSG_SECOND_COMMENT"//二级评论
    val MSG_WATCH = "MSG_WATCH"//关注
    val MSG_SECRET_LETTER = "MSG_SECRET_LETTER"//私信


    val PATH_WEBVIEW = "webview"//webview
    val PATH_COMMENT_DETAIL = "/home/comment_detail_acitivity"//评论详情
    val PATH_HOME_DETAIL = "/home/detail_acitivity"//首页详情
    val PATH_MESSAGE_DETAIL = "/message/message_detail"//消息列表
}