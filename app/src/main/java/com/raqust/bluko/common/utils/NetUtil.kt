package com.raqust.bluko.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager






/**
 * Created by linzehao
 * time: 2018/3/15.
 * info:网络工具类,不知道会不会有兼容问题
 */
object NetUtil {

    /**
     * 网络状态是否联通
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val manager = context
                .applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return false

        val networkinfo = manager.activeNetworkInfo

        return !(networkinfo == null || !networkinfo.isAvailable)

    }

    /**
     * 显示设置网络界面
     */
    fun show() {
        //http://blog.csdn.net/bzlj2912009596/article/details/70882300
    }

    /**
     * 判断WIFI网络是否可用
     */
    fun isWifiConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 判断MOBILE网络是否可用
     * @param context
     * @return
     */
    fun isMobileConnected(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 获取当前网络连接的类型信息
     * @param context
     * @return
     */
    fun getConnectedType(context: Context?): Int {
        if (context != null) {
            val mConnectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null && mNetworkInfo.isAvailable) {
                return mNetworkInfo.type
            }
        }
        return -1
    }

    /**
     * 获取当前的网络状态 ：没有网络0：WIFI网络1：3G网络2：2G网络3
     *
     * @param context
     * @return
     */
    fun getAPNType(context: Context): Int {
        var netType = 0
        val connMgr = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo ?: return netType
        val nType = networkInfo.type
        if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1// wifi
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            val nSubType = networkInfo.subtype
            val mTelephony = context
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS && !mTelephony.isNetworkRoaming) {
                netType = 2// 3G
            } else {
                netType = 3// 2G
            }
        }
        return netType
    }

    /** ***********************网络监听网络变化   *************************** **/
    /**
    public class NetWorkStatusReceiver extends BroadcastReceiver {

    public NetWorkStatusReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
    Toast.makeText(context, "network changed", Toast.LENGTH_LONG).show();
    BaseActivity.isNetWorkConnected = NetWorkUtils.getAPNType(context)>0;
    }
    }
    }

    <receiver
    android:name=".broadcast.NetWorkStatusReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter>
    <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
    </intent-filter>
    </receiver>

     **/

    /** ***********************网络监听网络变化   *************************** **/
}