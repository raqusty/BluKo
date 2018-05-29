package com.raqust.bluko.common.utils

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by linzehao
 * time: 2018/5/23.
 * info:
 */
object LogcatUtil{
    fun go(){

        var mLogcatProc = Runtime.getRuntime().exec(arrayOf("logcat", "Mytest:I *:S"))
        var reader =  BufferedReader( InputStreamReader(mLogcatProc.inputStream))
        var line:String

    }
}