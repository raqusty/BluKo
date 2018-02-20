package com.raqust.bluko.common.netKotlin

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by linzehao
 * time: 2018/2/20.
 * info:
 */
abstract class RetrofitListLoadCallBack<T> : Callback<ResponseBody> {

    var clazz: Class<T>
    var loading: Boolean = true
    var context: Context? = null

    constructor(clazz: Class<T>) : super() {
        this.clazz = clazz
        this.loading = loading
    }


    final override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        try {
            val body = String(response.body()!!.bytes())
            val json = JSONObject(body)
            val code = json.getInt("code")
            if (code == 200) {
                if (json.isNull("data")) {
                    onSuccess(call, json, listOf())
                    return
                }
                //FIXME 数组处理
                when (clazz) {
                    JSONObject::class.java -> {
                        val data = json.getJSONArray("data")
                        val list = (0 until data.length())
                                .map { data.getJSONObject(it) }
                        onSuccess(call, json, list as List<T>)
                    }
                    JSONArray::class.java -> {
                        val data = json.getJSONArray("data")
                        onSuccess(call, json, data as List<T>)
                    }
                    String::class.java -> {
                        val data = json.getJSONArray("data")
                        val list = (0 until data.length())
                                .map { data.getString(it) }
                        onSuccess(call, json, list as List<T>)
                    }
                    else -> {
                        val data = json.getJSONArray("data")
                        val gson = Gson()
//                val type = object : TypeToken<List<T>>(){}.type
//                val bean = Gson().fromJson(data.toString(), type) as List<T>
                        val list = (0 until data.length())
                                .map { data.getJSONObject(it) }
                                .map { gson.fromJson(it.toString(), clazz) }
                        onSuccess(call, json, list)
                    }
                }
            } else {
                onFailure(code, call, Exception(json.getString("msg")))
            }
        } catch (e: Exception) {
            onFailure(Contants.RESULT_CODE_MINUS_1, call, e)
        } finally {
        }
    }

    final override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        onFailure(Contants.RESULT_CODE_MINUS_2, call, t)
    }

    /**
     * 成功块
     */
    abstract fun onSuccess(call: Call<ResponseBody>, rawJson: JSONObject, bean: List<T>)

    /**
     * 错误块
     * @param isDispose     是否已经处理过错误信息
     * @param code        错误码
     */
    open fun onFailure(code: Int,
                       call: Call<ResponseBody>, t: Throwable) {
        //TODO 上线前移除
        if (!t.message.isNullOrEmpty()) {
        }
        Log.e("RetrofitCallBack", t.message ?: "")
    }

}
