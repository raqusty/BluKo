package com.raqust.bluko.module.testActivity

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import kotlinx.android.synthetic.main.activity_three_test.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.util.Log
import android.view.Display



/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
class Test3Activity : BaseActivity() {
    internal var url2 = "http://dev.raw.yiyoushuo.com/UGC/4acd78f0-f372-4edd-8f85-1224c6d4b8e9.png?x-oss-process=image/resize,m_fill,h_331,w_331,image/format,jpg"
    internal var url1 = "" //https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1532587960&di=30b52e2a826b48bb6bddc4e87fc957ce&src=http://alioss.g-cores.com/uploads/image/234c1e33-1d53-4058-8808-5de2417f6d99_watermark.jpg
    internal var url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1532587960&di=30b52e2a826b48bb6bddc4e87fc957ce&src=http://alioss.g-cores.com/uploads/image/234c1e33-1d53-4058-8808-5de2417f6d99_watermark.jpg&x-oss-process=image/resize,m_fill,h_576,w_576,image/format,jpg"

    override fun initViews() {
        val display = windowManager.defaultDisplay
        val heigth = display.width
        val  width = display.height
        Log.i("linzehao", "heigth $heigth  width $width")

        setPicture(url1)
    }
    override fun getToolBarResId() = 0

    override fun setListener() {
        text1.setOnClickListener {
            setPicture(url1)
        }

        text2.setOnClickListener {
            setPicture(url2)
        }

        text3.setOnClickListener {
            setPicture(url3)
        }

    }


    override fun initToolBar(navigationBarMgr: ToolBarManager?) {


    }

    override fun getContentViewResId() = R.layout.activity_three_test

    private fun setPicture(url: String) {
        Glide.with(mContext).load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
                .placeholder(R.mipmap.no_data_image)
                .error(R.mipmap.no_net_image)
                .into(image)
    }
}