package com.raqust.bluko.module.log.action

import android.util.Log
import com.raqust.bluko.module.log.LogConstant
import com.raqust.bluko.module.log.LogConstant.TAG
import com.raqust.bluko.module.log.SaveManager

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:滑动事件实现
 */
class SlideImpl {

    //一级缓存
    private var firstSlideCache = mutableListOf<String>()


    fun logAction(value: String, stop: Boolean = false) {
        firstSlideCache.add(value)
        val firstSize = firstSlideCache.size
        if (firstSize >= LogConstant.FIRST_SLIDE_CACHE) {
            firstSlideCache = firstSlideCache.subList(1, LogConstant.FIRST_SLIDE_CACHE)
        }
//        var str = ""
//        firstSlideCache.forEach {
//            str = str + it + " "
//        }
//        Log.i(TAG, "数据  " + str)
        if (stop) {
            SaveManager.saveSlideAction(firstSlideCache)
            firstSlideCache.clear()
        }

    }

}