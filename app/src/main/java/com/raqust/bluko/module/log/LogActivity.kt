package com.raqust.bluko.module.log

import android.view.View
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:
 */
class LogActivity : BaseActivity() {


    internal var TAG = "linzehao"

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

                LogManager.logClickAction("11")
                LogManager.logClickAction("22")
                LogManager.logClickAction("33")
            }
            R.id.text2 -> {
                for (i in 0 until 39) {
                    LogManager.logClickAction("" + i)
                }

            }
            R.id.text3 -> {
                LogManager.logStartTimeAction("11")
                LogManager.logStartTimeAction("22")
                LogManager.logStartTimeAction("33")
            }
            R.id.text4 -> {
                for (i in 0 until 39) {
                    LogManager.logStartTimeAction("" + i)
                }

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
