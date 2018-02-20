package com.raqust.bluko.module.net

import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.extend.toBody
import com.raqust.bluko.common.extend.toJsonString
import com.raqust.bluko.common.utils.PhoneUtil
import com.xingfeiinc.user.callback.RetrofitLoadCallBack
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call

/**
 * Created by linzehao
 * time: 2018/2/9.
 * info:
 */
class NetActivity : BaseActivity() {
    @BindView(R.id.text1)
    internal var QQLogin: TextView? = null

    @BindView(R.id.text2)
    internal var WeiBoLogin: TextView? = null

    @BindView(R.id.text3)
    internal var WeiXinLogin: TextView? = null

    @BindView(R.id.text4)
    internal var QQShare: TextView? = null

    @BindView(R.id.text5)
    internal var WeiBoShare: TextView? = null

    @BindView(R.id.text6)
    internal var WeiXinShare: TextView? = null

    internal var TAG = "linzehao"

    private val service by lazy { NetService.create() }

    override fun initViews() {
    }

    override fun setListener() {

    }

    override fun getToolBarResId(): Int {
        return 0
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager) {

    }

    private var isKey: Boolean? = false

    override fun getContentViewResId(): Int {
        return R.layout.activity_login
    }

    fun click(v: View) {
        when (v.id) {
            R.id.text1 -> {
                service.login(mapOf("type" to "1", "mobile" to "15989147263").toBody(),
                        mapOf("encryptMap" to "{\"password\":\"123456\"}").toJsonString())
                        .enqueue(object : RetrofitLoadCallBack<LoginEntity>(LoginEntity::class.java) {
                            override fun onSuccess(call: Call<ResponseBody>, rawJson: JSONObject, bean: LoginEntity?) {
                                Log.i("linzehao",bean.toString());
                            }

                            override fun onFailure(code: Int, call: Call<ResponseBody>, t: Throwable) {
                                Log.i("linzehao",code.toString());
                            }
                        })

                //mapOf("encryptMap","{\"password\":\"123456\"}")
            }
            R.id.text2 -> {
//                isKey = false
//
//                val body = RequestBody.create(MediaType.parse("application/json"), "{\"type\":1,\"password\":\"1234567\",\"mobile\":\"15989147263\"}")
//                val request = Request.Builder()
//                        .url("http://app.dev.gc.xf.io//login/v2?encryptMap={\"password\":\"123456\"}")
//                        .post(body)
//                        .build()
//
//                okHttpClient.newCall(request).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        e.printStackTrace()
//                        Log.d(TAG, "123  " + 123)
//                    }
//
//                    @Throws(IOException::class)
//                    override fun onResponse(call: Call, response: Response) {
//                        val body = response.body().string()
//                        Log.d(TAG, "312" + body)
//
//                    }
//                })
            }
            R.id.text3 -> {
                Log.i("linzehao",PhoneUtil.isWifi(this).toString())
            }
            R.id.text4 -> {
            }
            R.id.text5 -> {
            }
            R.id.text6 -> {
            }
            else -> {
            }
        }
    }

}