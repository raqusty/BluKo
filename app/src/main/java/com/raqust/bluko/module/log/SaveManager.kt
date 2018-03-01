package com.raqust.bluko.module.log

import android.util.Log
import com.raqust.bluko.module.log.LogConstant.FIRST_CACHE
import com.raqust.bluko.module.log.LogConstant.FIRST_SLIDE_CACHE
import com.raqust.bluko.module.log.LogConstant.SECOND_CACHE
import com.raqust.bluko.module.log.LogConstant.SECOND_SLIDE_CACHE
import com.raqust.bluko.module.log.LogConstant.TAG
import java.util.concurrent.ConcurrentLinkedQueue


/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:保存管理类
 */
object SaveManager {

    //loop是否存在
    private var isLoop = false

    //一级缓存
    private var firstCache = ConcurrentLinkedQueue<String>()

    //二级缓存
    private var secondCache = ConcurrentLinkedQueue<String>()

    //一级缓存
    private var firstSlideCache = ConcurrentLinkedQueue<String>()
    //滑动一级缓存
    private var secondSlideCache = ConcurrentLinkedQueue<String>()

    /**
     * 保存点击数据到一级缓存
     */
    fun saveClickAction(value: String) {
//        Log.i(TAG, "点击事件： " + value)
        firstCache.add(value)
        firToSecCache()
    }

    /**
     * 保存时间数据到一级缓存
     */
    fun saveTimeAction(value: String) {
        firstCache.add(value)
        firToSecCache()
    }

    /**
     * 保存滑动数据到一级缓存
     */
    fun saveSlideAction(value: String) {
        firstSlideCache.add(value)
        firToSecCachebySlide()
    }


    /**
     * 一级缓存 存 到二级缓存
     */
    @Synchronized
    private fun firToSecCache() {
        if (firstCache.size > FIRST_CACHE) {
            secondCache.addAll(firstCache)
            firstCache.clear()
        }
        if (secondCache.size > SECOND_CACHE && !isLoop) {
            startLoop()
        }
    }

    /**
     * 一级缓存 存 到二级缓存
     */
    @Synchronized
    private fun firToSecCachebySlide() {
        if (firstSlideCache.size > FIRST_SLIDE_CACHE) {
            secondSlideCache.addAll(firstSlideCache)
            firstSlideCache.clear()
        }
        if (secondSlideCache.size > SECOND_SLIDE_CACHE && !isLoop) {
            startLoop()
        }
    }

    /**
     * 开启一个循环
     */
    private fun startLoop() {
        isLoop = true
        kotlin.run {
            while (secondCache.size > 0) {
                val s = secondCache.poll()

                if (s != null) {
                    Log.i(TAG, "取出数据： " + s)
                }
            }
        }
        isLoop = false
    }

}