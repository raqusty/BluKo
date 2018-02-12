package com.raqust.bluko.module.net

import com.raqust.bluko.common.service.SignInterceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by linzehao
 * time: 2018/2/9.
 * info:
 */
interface NetService {
    companion object {
        private val retrofit by lazy {
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(SignInterceptor())
                    .build()

            Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
        }

        fun create(): NetService {
            return retrofit.baseUrl("http://app.dev.gc.xf.io").build().create(NetService::class.java)
        }
    }


    /**
     * 用户信息保存
     */
    @POST("login/v2")
    fun login(@Body map: RequestBody,@Query("encryptMap") pubKey:String = "" ): Call<ResponseBody>

}