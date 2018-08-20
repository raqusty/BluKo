package com.raqust.bluko.module.gsonActivity

import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import kotlinx.android.synthetic.main.activity_two_text.*

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
class TestActivity : BaseActivity() {
    override fun initViews() {

    }

    override fun getToolBarResId() = 0

    override fun setListener() {
        two.setOnClickListener {

        }
    }


    override fun initToolBar(navigationBarMgr: ToolBarManager?) {

    }

    override fun getContentViewResId() = R.layout.activity_two_text

}