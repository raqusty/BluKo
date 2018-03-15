package com.raqust.bluko.common.utils

/**
 * Created by linzehao
 * time: 2018/3/14.
 * info:
 * eg:    val version1 = "1.0.0"    val version2 = "1.1.1"
 * eg:VersionUtil.compareVersion(version1, version2).toString())
 */
object VersionUtil{

    /**
     * 0代表相等，1代表version1大于version2，-1代表version1小于version2
     */
    fun compareVersion(version1:String , version2:String ) :Int {
        if (version1.equals(version2)) {
            return 0
        }
        val version1Array = version1.split(".")
        val version2Array = version2.split(".")
        var index = 0
        // 获取最小长度值
        val minLen = Math.min(version1Array.size, version2Array.size)
        var diff = 0
        // 循环判断每位的大小

        while (index < minLen &&  (Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
            diff = (Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index]))
            index++
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (i in index until version1Array.size) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1
                }
            }

            for (i in index until version2Array.size) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1
                }
            }
            return 0
        } else {
            return if (diff > 0) 1 else -1
        }
    }

}