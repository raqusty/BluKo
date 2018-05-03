package com.raqust.bluko.module.Recycler

/**
 * Created by linzehao
 * time: 2018/5/3.
 * info:
 */
data class LogScrollEntity(
        var startTime: Long = 0L, //1
        var isLog: Boolean = false, //10
        var thresholdType: Int = 0, //false
        var content: String = ""//1
)