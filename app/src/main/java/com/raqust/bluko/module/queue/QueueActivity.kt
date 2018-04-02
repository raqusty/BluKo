package com.raqust.bluko.module.queue

import android.util.Log
import android.view.View
import cn.jiguang.share.android.api.JShareInterface
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager

/**
 * Created by linzehao
 * time: 2018/3/30.
 * info:
 */
class QueueActivity : BaseActivity() {


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

    fun click(v: View) {
        when (v.id) {
            R.id.text1 -> {
            }
            R.id.text2 -> {

            }
            R.id.text3 -> {
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