package com.raqust.bluko.module.log

import android.util.Log
import com.raqust.bluko.module.log.LogConstant.FIRST_CACHE
import com.raqust.bluko.module.log.LogConstant.TAG
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:保存管理类
 */
object SaveManager {

    //一级缓存
    private var firstCache = ConcurrentLinkedQueue<String>()

    //滑动一级缓存
    private var firstSlideCache = ConcurrentLinkedQueue<String>()

    //二级缓存
    private var secondCache = ConcurrentLinkedQueue<String>()

    /**
     * 保存点击数据到一级缓存
     */
    fun saveClickAction(value :String){
        Log.i(TAG,value)
        firstCache.add(value)

        firToSecCache()
    }

    /**
     * 保存时间数据到一级缓存
     */
    fun saveTimeAction(value :String){

    }

    /**
     * 保存滑动数据到一级缓存
     */
    fun saveSlideAction(value :String){

    }

    /**
     * 一级缓存 存 到二级缓存
     */
    private fun firToSecCache(){
        if (firstCache.size > FIRST_CACHE){
            secondCache.addAll(firstCache)
            firstCache.clear()
        }
    }

}