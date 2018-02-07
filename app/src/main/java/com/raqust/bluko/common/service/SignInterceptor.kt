package com.raqust.bluko.common.service

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.raqust.bluko.common.utils.RSAEncryptUtil
import com.raqust.bluko.common.utils.RSAEncryptUtil.publicKeyString
import okhttp3.*
import okio.Buffer
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by linzehao
 * time: 2018/2/7.
 * info:
 */
class SignInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //请求头添加token
        request = request.newBuilder().
//                addHeader("user-token", UserInfo.token).
                addHeader("Content-Type", "application/json;charset=utf-8").build()
        //追加加密数据
        request = encryptKey(request)

        //请求前
        var response = chain.proceed(request)
        //请求后

        return response
    }

    @Throws(IOException::class)
    private fun encryptKey(request: Request): Request {
        var encryptMap: Map<String, String>? = getEncryptMap(request) ?: return request

        if (request.method() == "GET") {
            var httpUrlBuilder = request.url().newBuilder().build().newBuilder()
            encryptMap?.forEach {
                httpUrlBuilder.setQueryParameter(it.key, RSAEncryptUtil.encryptByPublicKey(publicKeyString, it.value)).build()
            }
            val url = rmEncrypMap(httpUrlBuilder.build())
            return request.newBuilder().url(url).build()
        } else if (request.method() == "POST") {
            val buffer = Buffer()
            request.body().writeTo(buffer)
            val map = getPostMap(buffer.readString(Charset.forName("UTF-8"))) as HashMap<String, String>? //编码设为UTF-8
            encryptMap?.forEach {
                map?.put(it.key, RSAEncryptUtil.encryptByPublicKey(publicKeyString, it.value))
                val key = RSAEncryptUtil.encryptByPublicKey(publicKeyString, it.value)
//                Log.i("linzehao", "pubkey " + key)
//                Log.i("linzehao", "   key " + RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr, key))
            }

            val body = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(map))
            val url = rmEncrypMap(request.url())
            return request.newBuilder().url(url).post(body).build()

        }
        return request
    }

    private fun rmEncrypMap(url :HttpUrl):String{
        val  params =url.encodedQuery()
        val paramPair = params.split("&")
        var resultParam =""
        var resultUrl= url.toString()
        paramPair.forEach{
            val pair = it.split("=")
            if (pair.size == 2){
                if (pair[0] == "encryptMap"){
                    resultParam = pair[0] +"="+pair[1]
                }
            }
        }
        if(!TextUtils.isEmpty(resultParam)){
            resultUrl =  resultUrl.replaceFirst(resultParam,"",false)
        }
        return resultUrl
    }


    /**
     * 获取加密的map
     */
    private fun getEncryptMap(request: Request): Map<String, String>? {
        val mapString = request.url().newBuilder().build().queryParameter("encryptMap")
        if (TextUtils.isEmpty(mapString))
            return null
        return Gson().fromJson(mapString, HashMap<String, String>().javaClass)
    }

    /**
     * 获取post的map
     */
    private fun getPostMap(mapString: String): Map<String, String>? {
        if (TextUtils.isEmpty(mapString))
            return null
        return Gson().fromJson(mapString, HashMap<String, String>().javaClass)
    }
}