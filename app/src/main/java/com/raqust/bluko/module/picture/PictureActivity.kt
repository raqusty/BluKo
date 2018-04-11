package com.raqust.bluko.module.picture

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by linzehao
 * time: 2018/3/12.
 * info:
 */
class PictureActivity : BaseActivity() {


    override fun initViews() {

    }


    override fun setListener() {

    }

    override fun getToolBarResId(): Int {
        return 0
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager) {

    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_login
    }

    var msgid = 1

    fun click(v: View) {
        when (v.id) {
            R.id.text1 -> {

                Glide.with(mContext).load("http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.mipmap.no_data_image)
                        .error(R.mipmap.no_net_image)
                        .into(myimage)

            }
            R.id.text2 -> {
                Glide.with(mContext).load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201802/12/151842226050379936.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.mipmap.no_data_image)
                        .error(R.mipmap.no_net_image)
                        .into(myimage)
            }
            R.id.text3 -> {
                Glide.with(mContext).load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201803/23/152179050831779362493.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.mipmap.no_data_image)
                        .error(R.mipmap.no_net_image)
                        .into(myimage)
            }
            R.id.text4 -> {
                Glide.with(mContext).load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201803/23/152179050831979824288.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.mipmap.no_data_image)
                        .error(R.mipmap.no_net_image)
                        .into(myimage)

            }
            R.id.text5 -> {
                Glide.with(mContext).load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201803/23/152179050831779362493.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.mipmap.no_data_image)
                        .error(R.mipmap.no_net_image)
                        .into(myimage)
            }
            R.id.text6 -> {
                Glide.with(mContext).load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201803/23/152179050831779362493.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.mipmap.no_data_image)
                        .error(R.mipmap.no_net_image)
                        .into(myimage)
            }
            else -> {
            }
        }
    }


}
