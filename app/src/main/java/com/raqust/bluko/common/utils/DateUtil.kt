package com.raqust.bluko.common.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by linzehao
 * time: 2018/6/6.
 * info:
 */
object DateUtil {

    val ymdhmSdf by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm") }

    fun convertTimeToLong(time: String, format: SimpleDateFormat = ymdhmSdf): Long? {
        var date: Date? = null
        return try {
            date = format.parse(time)
            date!!.time
        } catch (e: Exception) {
            e.printStackTrace()
            0L
        }

    }
}