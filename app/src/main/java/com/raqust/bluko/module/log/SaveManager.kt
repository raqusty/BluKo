package com.raqust.bluko.module.log

import android.util.Log
import com.raqust.bluko.module.log.LogConstant.FIRST_CACHE
import com.raqust.bluko.module.log.LogConstant.TAG
import kotlin.concurrent.thread


/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:保存管理类
 */
object SaveManager {

    //loop是否存在
    private var isLoop = false
    private var isLoopSlide = false

    //一级缓存
    private var firstCache = mutableListOf<String>()

    private var queue = mutableListOf<String>()

    //滑动一级缓存
    private var firstSlideCache = mutableListOf<String>()

    private var slideQueue = mutableListOf<String>()


    /**
     * 保存点击数据到一级缓存
     */
    fun saveClickAction(value: String) {
//        Log.i(TAG, "存事件： " + value)
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
     * 一级缓存 存 到二级缓存
     */

    private fun firToSecCache() {
        if (firstCache.size > FIRST_CACHE) {
            if (!isLoop) {
                queue.addAll(firstCache)
                firstCache.clear()
                startLoop()
            } else {
                firstCache = firstCache.subList(1, FIRST_CACHE + 1)

                var str = ""
                firstCache.forEach {
                    str = str + it + " "
                }
                Log.i(TAG, "数据  " + str)
            }

        }
    }

    /**
     * 保存滑动数据到一级缓存
     * 如果数据
     */
    fun saveSlideAction(list: List<String>) {
        firstSlideCache.addAll(list)
        firToSecCacheBySlide()
    }

    /**
     * 一级缓存 存 到二级缓存
     */
    @Synchronized
    private fun firToSecCacheBySlide() {
        if (!isLoopSlide) {
            slideQueue.addAll(firstSlideCache)
            firstSlideCache.clear()
            startSlideLoop()
        }
    }

    /**
     * 开启一个循环
     */
    private fun startLoop() {
        isLoop = true
        thread(start = true) {
            while (queue.size > 0) {
                val s = queue.removeAt(0)
                Log.i(TAG, "取出数据： " + s)
                Thread.sleep(100)
            }
            isLoop = false
        }

    }

    /**
     * 开启一个循环
     */
    private fun startSlideLoop() {
        isLoopSlide = true
        thread(start = true) {
            while (slideQueue.size > 0) {
                val s = slideQueue.removeAt(0)
                Log.i(TAG, "取出数据： " + s)
                Thread.sleep(100)
            }
            isLoopSlide = false
        }

    }

}