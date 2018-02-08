package com.raqust.bluko.common.service

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.raqust.bluko.common.utils.RSAEncryptUtil
import com.raqust.bluko.common.utils.RSAEncryptUtil.publicKeyString
import com.raqust.bluko.common.utils.RsaEncrypt
import okhttp3.*
import okio.Buffer
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

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
        var newRequest = encryptKey(request)

        //请求前
        var response = chain.proceed(newRequest)
        //请求后

        val mediaType = response.body().contentType()
        val content = response.body().string()
//
        return encryptKeyAgain(content, request) ?: response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build()

    }


    /**
     * 公钥失效，重新加密请求并请求
     */
    private fun encryptKeyAgain(bodyString: String, request: Request): Response? {
        val json = JSONObject(bodyString)
        val code = json.getInt("code")
        if (code == 1231) {
//         PubKey.setPubKey(json.get("word").toString())
            var newRequest = encryptKey(request)
            val client = OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build()
            val call = client.newCall(newRequest)
            try {
                return call.execute()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return  null
    }

    /**
     * 请求前加密 encryptMap的代码
     */
    @Throws(IOException::class)
    private fun encryptKey(request: Request): Request {
        var encryptMap: Map<String, String>? = getEncryptMap(request) ?: return request

        if (request.method() == "GET") {
            var httpUrlBuilder = request.url().newBuilder().build().newBuilder()
            encryptMap?.forEach {
                httpUrlBuilder.setQueryParameter(it.key, RsaEncrypt.encryptByPublicKey( it.value,publicKeyString)).build()
            }
            val url = rmEncrypMap(httpUrlBuilder.build())
            return request.newBuilder().url(url).build()
        } else if (request.method() == "POST") {
            val buffer = Buffer()
            request.body().writeTo(buffer)
            val map = getPostMap(buffer.readString(Charset.forName("UTF-8"))) as HashMap<String, String>? //编码设为UTF-8
            encryptMap?.forEach {
                map?.put(it.key, RsaEncrypt.encryptByPublicKey(it.value,publicKeyString))
                val key = RsaEncrypt.encryptByPublicKey(it.value,publicKeyString)
                Log.i("linzehao", "pubkey " + key)
                Log.i("linzehao", "   key " + RsaEncrypt.decryptByPrivateKey(key,RSAEncryptUtil.privateKeyStr))
            }

            val body = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(map))
            val url = rmEncrypMap(request.url())
            return request.newBuilder().url(url).post(body).build()

        }
        return request
    }

    /**
     * 移除 encryptMap的
     */
    private fun rmEncrypMap(url: HttpUrl): String {
        val params = url.encodedQuery()
        val paramPair = params.split("&")
        var resultParam = ""
        var resultUrl = url.toString()
        paramPair.forEach {
            val pair = it.split("=")
            if (pair.size == 2) {
                if (pair[0] == "encryptMap") {
                    resultParam = pair[0] + "=" + pair[1]
                }
            }
        }
        if (!TextUtils.isEmpty(resultParam)) {
            resultUrl = resultUrl.replaceFirst(resultParam, "", false)
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