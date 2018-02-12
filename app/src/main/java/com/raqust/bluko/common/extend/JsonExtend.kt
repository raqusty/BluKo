package com.raqust.bluko.common.extend

import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by linzehao
 * time: 2018/2/9.
 * info:
 */
/**
 * List转RequestBody
 */
fun List<*>.toBody(): RequestBody {
    val json = JSONArray(this)
    return json.toBody()
}

/**
 * Map转RequestBody
 */
fun Map<*, *>.toBody(): RequestBody {
    val json = JSONObject(this)
    return json.toBody()
}

/**
 * Map转JsonString
 */
fun Map<*, *>.toJsonString(): String {
    val json = JSONObject(this)
    return json.toString()
}

/**
 * JSONObject转RequestBody
 */
fun JSONObject.toBody(): RequestBody {
    return RequestBody.create(MediaType.parse("Content-Type, application/json"), this.toString())
}

/**
 * JSONArray转RequestBody
 */
fun JSONArray.toBody(): RequestBody {
    return RequestBody.create(MediaType.parse("Content-Type, application/json"), this.toString())
}