package com.raqust.bluko.module.log

import android.util.Log
import com.raqust.bluko.module.log.LogConstant.FIRST_CACHE
import com.raqust.bluko.module.log.LogConstant.FIRST_SLIDE_CACHE
import com.raqust.bluko.module.log.LogConstant.SECOND_CACHE
import com.raqust.bluko.module.log.LogConstant.SECOND_SLIDE_CACHE
import com.raqust.bluko.module.log.LogConstant.TAG
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread


/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:保存管理类
 */
object SaveManager {

    //loop是否存在
    private var isLoop = false

    //一级缓存
    private var firstCache =mutableListOf <String>()

    //二级缓存
    private var secondCache = mutableListOf<String>()

    private var queue = mutableListOf<String>()

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

    private fun firToSecCache() {
        if (firstCache.size > FIRST_CACHE) {
            secondCache.addAll(firstCache)
//            Log.i(TAG, "二级缓存   " + secondCache.size)
            firstCache.clear()
            
        }
        if (secondCache.size > SECOND_CACHE ) {
            Log.i(TAG,"132423  "+isLoop)
            if(!isLoop){
                queue.addAll(secondCache)
                secondCache.clear()
                startLoop()    
            }else{
                Log.i(TAG,"333  "+secondCache.size)
                secondCache = secondCache.subList(secondCache.size - SECOND_CACHE,SECOND_CACHE)
                Log.i(TAG,"444  "+secondCache.size)
                secondCache.forEach{
                    Log.i(TAG,"secondCache  "+it)
                }
            }
            
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
        thread(start = true) {
            while (queue.size > 0) {
                val s = queue.removeAt(0)
                Log.i(TAG, "取出数据： " + s)
                Thread.sleep(1000)
            }
            isLoop = false
        }

    }

}