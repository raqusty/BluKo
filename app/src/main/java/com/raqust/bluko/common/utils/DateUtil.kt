package com.raqust.bluko.common.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by linzehao
 * time: 2018/6/6.
 * info:
 *
 *       val day1 = "2018-6-6 20:02"
val day2 = "2018-6-7 0:02"
val day3 = "2018-7-6 10:02"
val day4 = "2018-7-7 10:02"

val day1Date = Date(DateUtil.convertTimeToLong(day1) ?: 0)
val day2Date = Date(DateUtil.convertTimeToLong(day2) ?: 0)
val day3Date = Date(DateUtil.convertTimeToLong(day3) ?: 0)
val day4Date = Date(DateUtil.convertTimeToLong(day4) ?: 0)

val diff_1 = day2Date[6] - day1Date[6]
val diff_2 = day3Date[6] - day1Date[6]
val diff_3 = day3Date[6] - day2Date[6]

val diff_4 = day4Date[6] - day1Date[6]
val diff_5 = day4Date[6] - day2Date[6]
val diff_6 = day4Date[6] - day3Date[6]
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