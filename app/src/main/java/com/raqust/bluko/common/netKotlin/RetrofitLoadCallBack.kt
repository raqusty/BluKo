package com.xingfeiinc.user.callback

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.raqust.bluko.common.netKotlin.Contants.RESULT_CODE_200
import com.raqust.bluko.common.netKotlin.Contants.RESULT_CODE_MINUS_1
import com.raqust.bluko.common.netKotlin.Contants.RESULT_CODE_MINUS_2
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Retrofit带加载状态处理的Callback
 * Created by linzehao on 17-10-24.
 */
abstract class RetrofitLoadCallBack<T> : Callback<ResponseBody> {

    var clazz: Class<T>
    var loading: Boolean = true
    var context: Context? = null

    constructor(clazz: Class<T>, loading: Boolean = true) : super() {
        this.clazz = clazz
        this.loading = loading
    }


    final override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        try {
            val body = String(response.body()!!.bytes())
            val json = JSONObject(body)
            val code = json.getInt("code")
            if (code == RESULT_CODE_200) {
                if (json.isNull("data")) {
                    onSuccess(call, json, null)
                    dismissLoading()
                    return
                }
                when (clazz) {
                    JSONObject::class.java -> {
                        val data = json.getJSONObject("data")
                        onSuccess(call, json, data as T)
                    }
                    JSONArray::class.java -> {
                        val data = json.getJSONArray("data")
                        onSuccess(call, json, data as T)
                    }
                    String::class.java -> {
                        val data = json.getString("data")
                        onSuccess(call, json, data as T)
                    }
                    else -> {
                        val data = json.getJSONObject("data")
                        val bean = Gson().fromJson(data.toString(), clazz)
                        onSuccess(call, json, bean)
                    }
                }
                dismissLoading()
            } else {
                onFailure(code, call, Exception(json.getString("msg")))
                dismissLoading()
            }
        } catch (e: Exception) {
//            Log.d("xingfei",e.message)
            //Todo 这里不应该给异常的，直接处理完返回msg就好了
            onFailure(RESULT_CODE_MINUS_1, call, e)
            dismissLoading()
        } finally {
            dismissLoading()
        }
    }

    final override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//        Log.d("xingfei",t.message)
        //Todo 这里不应该给异常的，直接处理完返回msg就好了
        onFailure(RESULT_CODE_MINUS_2, call, t)
        dismissLoading()
    }

    /**
     * 成功块
     */
    abstract fun onSuccess(call: Call<ResponseBody>, rawJson: JSONObject, bean: T?)

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
        dismissLoading()
    }


    private fun dismissLoading() {
    }
}
