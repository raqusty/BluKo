package com.raqust.bluko.common.utils

/**
 * Created by linzehao
 * time: 2018/2/20.
 * info:
 */

import android.annotation.SuppressLint
import android.content.Context
import com.raqust.bluko.MyApplication
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * SharedPreference用kotlin委托方式存取
 * @see <a href="http://www.cnblogs.com/magics/p/6148966.html">Kotlin偏好设置</a>
 */
class PreferStorage<T>(private val default: T, private val key: String? = null) : ReadWriteProperty<Any?, T> {
    private val context by lazy { MyApplication.instance }
    private val pref by lazy { context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val key = this.key ?: property.name
        return findValue(key, default) as T
    }

    private fun findValue(key: String, default: T): Any {
        return when (default) {
            is Int -> pref.getInt(key, default)
            is Float -> pref.getFloat(key, default)
            is Long -> pref.getLong(key, default)
            is String -> pref.getString(key, default)
            is Boolean -> pref.getBoolean(key, default)
            else -> throw IllegalArgumentException("This type can not be saved")
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val key = this.key ?: property.name
        with(pref.edit()){
            when(value){
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                else -> throw IllegalArgumentException("This type can not be saved")
            }
        }.apply()
    }
}