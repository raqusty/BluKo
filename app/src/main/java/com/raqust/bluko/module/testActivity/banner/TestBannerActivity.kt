package com.raqust.bluko.module.testActivity.banner

import android.util.Log
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.activity_test_banner.*

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
class TestBannerActivity : BaseActivity() {
    override fun initViews() {
        val photoList = mutableListOf("http://www.cpds.cc/data/jdt/20180724104905__0.png",
                "http://www.cpds.cc/data/jdt/20180724101121__100155.jpg",
                "http://www.cpds.cc/data/jdt/20180723101112__195987.jpg",
                "http://www.cpds.cc/data/jdt/20180723101139__100195.jpg")
        val titleList = mutableListOf("世界杯竞足急速返奖", "", "roma", "")

        banner.setImageLoader(GlideImageLoader())
                .setImages(photoList)
                .setOnBannerListener(object : OnBannerListener {
                    override fun onBannerClick(position: Int) {
                        Log.i("linzehao","sfadf")
                    }})
//        banner.setBannerTitles(entity.title)
                .start()
    }

    override fun getToolBarResId() = 0

    override fun setListener() {
    }


    override fun initToolBar(navigationBarMgr: ToolBarManager?) {


    }

    override fun getContentViewResId() = R.layout.activity_test_banner


}