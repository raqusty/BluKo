package com.raqust.bluko.module.testActivity

import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import com.raqust.bluko.common.wrapper1.WhiteIntentWrapper1
import kotlinx.android.synthetic.main.activity_two_text.*

/**
 * Created by linzehao
 * time: 2018/5/14.
 * info:
 */
class WhiteWrapperActivity : BaseActivity() {


    override fun initViews() {
    }

    override fun getToolBarResId() = 0

    override fun setListener() {
        one.setOnClickListener {
            WhiteIntentWrapper.whiteListMatters(this, "test")
        }
        two.setOnClickListener {
            WhiteIntentWrapper1.whiteListMatters(this,"test")
        }
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId() = R.layout.activity_two_text

}