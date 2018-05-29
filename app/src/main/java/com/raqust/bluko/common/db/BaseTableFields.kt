package com.raqust.bluko.common.db

/**
 * 基本表字段
 * Created by linzehao on 18-5-28.
 */
@Suppress("PropertyName")
internal interface BaseTableFields {
    //表名
    val TABLE_NAME: String
    //主键ID
    val PRIMARY_KEY_ID: String
}