package com.raqust.bluko.module.photo

import android.view.View
import com.bumptech.glide.Glide
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import uk.co.senab.photoview.PhotoView

/**
 * Created by linzehao
 * time: 2018/2/20.
 * info:
 */
class PhotoActivity : BaseActivity() {
    override fun initViews() {

        val photoView = findViewById(R.id.photo_view) as PhotoView
        Glide.with(mContext)
                .load("http://xf-gc-test-oss.oss-cn-hangzhou.aliyuncs.com/jpg/201802/12/151842226050379936.jpg")
//                .centerCrop()
                .skipMemoryCache(true)
                .into(photoView)
    }

    override fun getToolBarResId(): Int {
        return 0
    }


    override fun setListener() {
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId(): Int = R.layout.activity_photo



}