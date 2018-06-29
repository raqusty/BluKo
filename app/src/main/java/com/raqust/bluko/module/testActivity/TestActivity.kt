package com.raqust.bluko.module.testActivity

import android.content.Intent
import android.util.Log
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.extend.get
import com.raqust.bluko.common.utils.DateUtil
import com.raqust.bluko.common.utils.NotificationsUtils
import com.raqust.bluko.common.wrapper.DialogImpl
import kotlinx.android.synthetic.main.activity_two_text.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
class TestActivity : BaseActivity() {
    override fun initViews() {
    }

    override fun getToolBarResId() = 0

    var count = 0
    var lastTime = 0L

    val httpClient by lazy {
        OkHttpClient.Builder().connectTimeout(1, TimeUnit.MILLISECONDS).writeTimeout(1, TimeUnit.MILLISECONDS)
                .readTimeout(1, TimeUnit.MILLISECONDS)
                .build()
    }

    override fun setListener() {
        one.setOnClickListener {

            val day1 = "2018-6-6 20:02"
            val day2 = "2018-6-7 0:02"
            val day3 = "2018-7-6 10:02"
            val day4 = "2018-7-7 10:02"

            val day1Date = Date(DateUtil.convertTimeToLong(day1) ?: 0)
            val day2Date = Date(DateUtil.convertTimeToLong(day2) ?: 0)
            val day3Date = Date(DateUtil.convertTimeToLong(day3) ?: 0)
            val day4Date = Date(DateUtil.convertTimeToLong(day4) ?: 0)

            val diff_1 = day2Date[6] - day1Date[6]
            val diff_2 = day3Date[6] - day1Date[6]
            val diff_3 = day3Date[6] - day2Date[6]

            val diff_4 = day4Date[6] - day1Date[6]
            val diff_5 = day4Date[6] - day2Date[6]
            val diff_6 = day4Date[6] - day3Date[6]


            Log.i("linzehao", "diff_1 $diff_1 , diff_2 $diff_2 , diff_3 $diff_3 , diff_4 $diff_4 , diff_5 $diff_5 , diff_6 $diff_6 ")


//            thread {
//                val request = Request.Builder()
//                        .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
//                        .build()
//                try {
//                    val response = httpClient.newCall(request).execute();
//                    Log.i("linzehao", "Response completed: " + response)
//                } catch (e: Exception) {
//                    Log.i("linzehao", "Timer out " + e.printStackTrace())
//                }
//            }
            NotificationsUtils.clearNotication(this)
        }
        //http://api.iwanbei.com/user/v1/info/myUserStatistics
        two.setOnClickListener {
            //            val retrofit = Retrofit.Builder()
//                    .client(httpClient)
//                    .addConverterFactory(GsonConverterFactory.create()).baseUrl("http://api.iwanbei.com/").build()
//            val request = retrofit.create(RequestInterface::class.java)
//
//            request.getArticleByLatest().enqueue(object : Callback<ResponseBody> {
//                override fun onResponse(call: Call<ResponseBody>?, response: retrofit2.Response<ResponseBody>?) {
//
//                }
//
//                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
//                    if (t is SocketTimeoutException){
//                        Log.i("linzehao","sdfadsf")
//                    }
//                }
//            })
            val intent = Intent("sdfsdf")

            NotificationsUtils.handleNotification(this,"sadf","fasdf","1111",intent)

        }
    }


    override fun initToolBar(navigationBarMgr: ToolBarManager?) {


    }

    override fun getContentViewResId() = R.layout.activity_two_text

    interface RequestInterface {


        @GET("user/v1/info/myUserStatistics")
        fun getArticleByLatest(): Call<ResponseBody>

    }
}

