package com.raqust.bluko.module.gsonActivity

/**
 * Created by linzehao
 * time: 2018/7/31.
 * info:
 */

data class jsonEntity(
    val code: Int,
    val result: Result
)

data class Result(
    val nextpage: Int,
    val page: Int,
    val data: Data
)

data class Data(
    val title: String,
    val list: List<X>
)

data class X(
    val star: String,
    val tags: List<Tag>,
    val title: String,
    val id: String,
    val icon: String,
    val num_size_lang: String,
    val downinfo: Downinfo
)

data class Downinfo(
    val id: String,
    val icon: String,
    val packag: String,
    val appname: String,
    val appinfo: String,
    val md5: String,
    val version: String,
    val apkurl: String,
    val size_m: String,
    val size_byte: String,
    val status: String,
    val need_gplay: Boolean,
    val sdk_version: Int,
    val ppk_list: List<Any>,
    val custom_pop: Int,
    val custom_pop_txt: String,
    val official_link: String,
    val official_show: String
)

data class Tag(
    val id: String,
    val title: String,
    val link: String,
    val state: String
)