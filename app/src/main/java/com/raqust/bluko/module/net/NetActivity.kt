package com.raqust.bluko.module.net

import android.Manifest
import android.annotation.SuppressLint
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.eagle.mixsdk.sdk.did.ThinkFlyUtils
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.extend.toBody
import com.raqust.bluko.common.extend.toJsonString
import com.raqust.bluko.common.utils.PhoneUtil
import com.raqust.bluko.module.user.UserInfo
import com.xingfeiinc.user.callback.RetrofitLoadCallBack
import okhttp3.ResponseBody
import org.json.JSONObject
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import android.graphics.drawable.Drawable




/**
 * Created by linzehao
 * time: 2018/2/9.
 * info:
 */
class NetActivity : BaseActivity(), EasyPermissions.PermissionCallbacks {
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
    var page = 1

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

    @SuppressLint("MissingPermission")
    fun click(v: View) {
        when (v.id) {
            R.id.text1 -> {
                service.login(mapOf("type" to "1", "mobile" to "15989147263").toBody(),
                        mapOf("password" to "1234567").toJsonString())
                        .enqueue(object : RetrofitLoadCallBack<LoginEntity>(LoginEntity::class.java) {
                            override fun onSuccess(call: Call<ResponseBody>, rawJson: JSONObject, bean: LoginEntity?) {
                                Log.i("linzehao", bean.toString());
                                UserInfo.setUser(bean)
                            }

                            override fun onFailure(code: Int, call: Call<ResponseBody>, t: Throwable) {
                                Log.i("linzehao", code.toString());
                            }
                        })

                //mapOf("encryptMap","{\"password\":\"123456\"}")
            }
            R.id.text2 -> {
                service.latest(mapOf("pageSize" to "20", "page" to page.toString()).toBody())
                        .enqueue(object : RetrofitLoadCallBack<IndexEntity>(IndexEntity::class.java) {
                            override fun onSuccess(call: Call<ResponseBody>, rawJson: JSONObject, bean: IndexEntity?) {
                                Log.i("linzehao", bean.toString())
                                page++
                            }

                            override fun onFailure(code: Int, call: Call<ResponseBody>, t: Throwable) {
                                Log.i("linzehao", code.toString());
                            }
                        })
            }
            R.id.text3 -> {
                Log.i("linzehao", PhoneUtil.isWifi(this).toString())
            }
            R.id.text4 -> {
            }
            R.id.text5 -> {
                Log.i("linzehao", "test_image   " + ThinkFlyUtils.getDeviceID(this))
            }
            R.id.text6 -> {
                if (!EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    EasyPermissions.requestPermissions(this, "需要获取权限，请点确定",
                            123, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                } else {
                    val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                    val imei = telephonyManager.deviceId
                    Log.i("linzehao", "imei  " + imei)
                }


            }
            else -> {
            }
        }
    }

    //失败
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        Log.i("linzehao", "imei  " + 456)
    }

    //成功
    @SuppressLint("MissingPermission")
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
        val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        val imei = telephonyManager.deviceId
        Log.i("linzehao", "imei  " + imei)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.i("linzehao", "imei  " + 123)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        Log.i("linzehao", "imei  " + 234)
    }

}