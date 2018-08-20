package com.raqust.bluko.module.testActivity

import android.content.Intent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import kotlinx.android.synthetic.main.activity_two_text.*

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
class Test2Activity : BaseActivity() {
    override fun initViews() {
        Glide.with(mContext).load("http://image-demo.oss-cn-hangzhou.aliyuncs.com/example.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .thumbnail(0.5f)
                .placeholder(R.mipmap.no_data_image)
                .error(R.mipmap.no_net_image)
                .into(three)

    }

    override fun getToolBarResId() = 0

    override fun setListener() {
        one.setOnClickListener {
            val intent = Intent(this, Test2Activity::class.java)
            startActivity(intent)

        }
        two.setOnClickListener {

        }
    }


    override fun initToolBar(navigationBarMgr: ToolBarManager?) {


    }

    override fun getContentViewResId() = R.layout.activity_two_text

}