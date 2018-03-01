package com.raqust.bluko.module.log.entity

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:
 */
//通用数据字段//业务行为字段
data class LogEntity(
        var aid: String = "", //内容id
        var author: String = "",//内容发布者id
        var weight: Int = 0,//文章基础权重
        var topics: String = "",//内容包含的话题id列表
        var utags: String = "",//用户的标签列表
        var tags: String = "",//文章的标签列表
        var action: String = "", //用户操作行为
        var page: String = "",//页面名称
        var cat: String = "",//栏目名称
        var business: String = "",//业务参数
        var extension: String = "",//扩展参数
        var timestamp: Long = System.currentTimeMillis(),//时间戳
        var remark : String = ""//备注
)
