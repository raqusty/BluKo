package com.raqust.bluko.module.log.action

import com.raqust.bluko.module.log.LogConstant
import com.raqust.bluko.module.log.entity.LogEntity

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:
 */
class LogAction {
    //登陆成功
    fun logClickLogin(cat:String): LogEntity {
        val entity = LogEntity()
        entity.action = LogConstant.LOGINSUCES
        entity.cat = cat
        return entity
    }

    //点击首页精选顶部的选择兴趣
    fun logClickHomeInterest(page:String): LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_INTEREST
        entity.page = page
        return entity
    }

    //在我的中点击选择兴趣
    fun logClickPersonalInterest(page:String): LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_INTEREST
        entity.page = page
        return entity
    }

    //点击导航栏
    fun logClickNavigation(cat:String): LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_NAVIGATION
        entity.cat = cat
        return entity
    }

    //在底部导航栏中点击发布按钮
    fun logClickNavigationRelease(): LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_SEND
        return entity
    }

    //在话题中点击发布按钮
    fun logClickTopicRelease(page:String): LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_SEND
        entity.page = page
        return entity
    }

    //发布成功
    fun logClickReleaseSuccess(aid: String): LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_SEND
        entity.business = aid
        return entity
    }

    //发布失败
    fun logClickReleaseFail(): LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.SENDFAIL
        return entity
    }

    //点击顶部的精选，进入精选页
    fun logClickSelected(page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_CLICK
        entity.page = page
        return entity
    }

    //点击顶部的关注，进入关注页
    fun logClickFollow(page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_CLICK
        entity.page = page
        return entity
    }

    //点击顶部的分类，进入分类页
    fun logClickSort(page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_CLICK
        entity.page = page
        return entity
    }

    //首页精选访问时长
    fun logSelectedTime(page: String,remark:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.VIEWTIME
        entity.page = page
        entity.remark = remark
        return entity
    }

    //首页关注访问时长
    fun logFollowTime(page: String,remark: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.VIEWTIME
        entity.page = page
        entity.remark = remark
        return entity
    }

    //在关注页下，切换话题
    fun logFollowSwitchTopic() : LogEntity{
        val entity = LogEntity()
        entity.action = "btn_click"
        entity.page = "follow"
        entity.cat = "ht"
        return entity
    }

    //在关注页下，切换用户
    fun logFollowSwitchUser(page: String,cat: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_CLICK
        entity.page = page
        entity.cat = cat
        return entity
    }

    //在分类页下，切换热度
    fun logSortSwitchHeat(page: String,cat: String,sortid:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_CLICK
        entity.page = page
        entity.cat = cat
        entity.business = sortid
        return entity
    }

    //在分类页下，切换时间
    fun logSortSwitchTime(page: String,cat: String,sortid:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_CLICK
        entity.page = page
        entity.cat = cat
        entity.business = sortid
        return entity
    }

    //分类页点击各分类名称，进入分类页
    fun logSortClick(page: String,sortid:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_SORTPAGE
        entity.page = page
        entity.business = sortid
        return entity
    }

    //在分类页切换分类
    fun logSwitchSort(page: String,sortid:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_SORTPAGE
        entity.page = page
        entity.business = sortid
        return entity
    }

    //各分类页访问时长
    fun logSortVisitTime(page: String,sortid:String,remark:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.VIEWTIME
        entity.page = page
        entity.business = sortid
        entity.remark = remark
        return entity
    }

    //在信息流点击标签进入标签页
    fun logInfoLabel(page: String,tagid:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_TAG
        entity.page = page
        entity.business = tagid
        return entity
    }

    //在信息流详情页点击标签进入标签页
    fun logInfoDetailsLabel(page: String,tagid:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_TAG
        entity.page = page
        entity.business = tagid
        return entity
    }

    //点击不感兴趣，选择原因
    fun logDisincline(page: String,cat: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_DISLIKE
        entity.page = page
        entity.cat = cat
        entity.business = aid
        return entity
    }

    //在首页关注直接点击评论
    fun logFollowComment(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_REPLY
        entity.page = page
        entity.business = aid
        return entity
    }

    //在首页精选直接点击评论
    fun logSelectedComment(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_REPLY
        entity.page = page
        entity.business = aid
        return entity
    }

    //在首页分类页直接点击评论
    fun logSortComment(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_REPLY
        entity.page = page
        entity.business = aid
        return entity
    }

    //在标签页直接点击评论
    fun logLabelComment(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_REPLY
        entity.page = page
        entity.business = aid
        return entity
    }

    //在话题页直接点击评论
    fun logTopicComment(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_REPLY
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流评论成功
    fun logInfoCommentSuccess(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.REPLYSUCES
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流评论失败
    fun logInfoCommentFail(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.REPLYFAIL
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流转发成功
    fun logInfoForwardSuccess(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.FORWARDSUCES
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流转发失败
    fun logInfoForwardFail(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.FORWARDFAIL
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流（首页等）直接点赞
    fun logInfoLike(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_LIKE
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流（首页等）直接转发
    fun logInfoForward(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_FORWARD
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流（首页等）点击图片
    fun logInfoClickPicture(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_PICTURE
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流（首页等）点击视频
    fun logInfoClickVideo(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_VIDEO
        entity.page = page
        entity.business = aid
        return entity
    }

    //从首页精选进入详情页
    fun logselectedDetails(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从首页关注进入详情页
    fun logfollowDetails(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从首页分类页进入详情页
    fun logSortDetails(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从首页分类页进入详情页
    fun logClickSearchDetails(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从标签进入详情页
    fun logClickLabelDetails(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从话题页进入详情页
    fun logClickTopicDetails(page: String,aid: String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从相关推荐进入详情页
    fun logClickRelevantDetails(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从知识库进入详情页
    fun logClickLibraryDetails(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从个人主页进入详情页
    fun logClickMyDetails(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从推送/通知栏进入详情页
    fun logClickPushDetails(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //从消息通知进入详情页
    fun logClickNoticeDetails(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.PAGE_FEED
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页评论
    fun logClickNewsComment(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_REPLY
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页点赞
    fun logClickNewsLike(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_LIKE
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页转发
    fun logClickNewsForward(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_FORWARD
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页点击图片
    fun logClickNewsPicture(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_PICTURE
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页点击视频
    fun logClickNewsVideo(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_VIDEO
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页评论成功
    fun logClickNewsCommentSuccess(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.REPLYSUCES
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页评论失败
    fun logClickNewsCommentFail(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.REPLYFAIL
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页转发成功
    fun logClickNewsForwardSuccess(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.FORWARDSUCES
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页转发失败
    fun logClickNewsForwardFail(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.FORWARDFAIL
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页收藏
    fun logClickNewsFavorite(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_FAVORITE
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页举报
    fun logClickNewsReport(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_TIP
        entity.page = page
        entity.business = aid
        return entity
    }

    //在信息流详情页点赞一级评论
    fun logClickNewsLikeOne(aid:String,page:String) : LogEntity{
        val entity = LogEntity()
        entity.action = LogConstant.BTN_TIP
        entity.page = page
        entity.business = aid
        return entity
    }
}