package com.raqust.bluko.module.log

import com.raqust.bluko.module.log.action.ClickImpl
import com.raqust.bluko.module.log.action.SlideImpl
import com.raqust.bluko.module.log.action.TimeImpl

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:日志管理类
 */
object LogManager{
    //点击事件的实例
    private val clickImpl by lazy { ClickImpl() }

    //时间事件的实例
    private val timeImpl by lazy { TimeImpl() }

    //滑动事件的实例
    private val slideImpl by lazy { SlideImpl() }

    /**
     * 点击事件统计
     */
    fun logClickAction(value :String){
        clickImpl.logAction(value)
    }

    /**
     * 进入页面时间事件统计
     */
    fun logStartTimeAction(value :String){
        timeImpl.logAction(value)
    }

    /**
     * 退出 页面时间事件统计
     */
    fun logStopTimeAction(value :String){
        timeImpl.logAction(value)
    }

    /**
     * 滑动事件统计
     */
    fun logSlideAction(value :String){
        slideImpl.logAction(value)
    }
}
