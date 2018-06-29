package com.raqust.bluko.common.extend

import java.util.*

/**
 * Created by linzehao
 * time: 2018/6/6.
 * info:
 */
enum class DateOptUnit {
    YEAR, MONTH, DATE;

    fun parseType(): Int {
        return when (this) {
            YEAR -> Calendar.DATE
            MONTH -> Calendar.MONTH
            DATE -> Calendar.DATE
        }
    }
}

data class DateOperator(val unit: DateOptUnit, val value: Int)

/**
 * date+1
 * 往后的几天
 */
operator fun Date.plus(nextVal: Int): Date {
    val calendar = GregorianCalendar()
    calendar.time = this
    calendar.add(Calendar.DATE, nextVal)
    return calendar.time
}

/**
 * date-1
 */
operator fun Date.minus(nextVal: Int): Date {
    val calendar = GregorianCalendar()
    calendar.time = this
    calendar.add(Calendar.DATE, nextVal * -1)
    return calendar.time
}

/**
 * date+year(3)
 * 往后的几天
 */
operator fun Date.plus(nextVal: DateOperator): Date {
    val calendar = GregorianCalendar()
    calendar.time = this
    calendar.add(nextVal.unit.parseType(), nextVal.value)
    return calendar.time
}

/**
 * date-month(4)
 */
operator fun Date.minus(nextVal: DateOperator): Date {
    val calendar = GregorianCalendar()
    calendar.time = this
    calendar.add(nextVal.unit.parseType(), nextVal.value * -1)
    return calendar.time
}

/**
 * 得到月末
 */
operator fun Date.inc(): Date {
    val calendar = GregorianCalendar()
    calendar.time = this
    calendar.add(Calendar.MONTH, 1)
    calendar.set(Calendar.DAY_OF_MONTH, 0)
    return calendar.time
}

/**
 * 得到月初
 */
operator fun Date.dec(): Date {
    val calendar = GregorianCalendar()
    calendar.time = this
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return calendar.time
}

/**
 * 取 年月日时分秒 0 - 5
 * 例如 2015-12-21 22:15:56
 * date[0]:2015  date[1]:12 date[2]:21
 */
operator fun Date.get(position: Int): Int {
    val calendar = GregorianCalendar()
    calendar.time = this
    var value = 0
    when (position) {
        0 -> value = calendar.get(Calendar.YEAR)
        1 -> value = calendar.get(Calendar.MONTH) + 1
        2 -> value = calendar.get(Calendar.DAY_OF_MONTH)
        3 -> value = calendar.get(Calendar.HOUR)
        4 -> value = calendar.get(Calendar.MINUTE)
        5 -> value = calendar.get(Calendar.SECOND)
        6 -> value = calendar.get(Calendar.DAY_OF_YEAR)
    }
    return value
}

/**
 * 比较2个日期
 * if(date1 > date2) {
 * }
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
operator fun Date.compareTo(compareDate: Date): Int {
    return (time - compareDate.time).toInt()
}