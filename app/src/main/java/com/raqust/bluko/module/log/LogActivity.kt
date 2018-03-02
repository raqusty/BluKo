package com.raqust.bluko.module.log

import android.util.Log
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

    var list = mutableListOf<String>()

    override fun initViews() {

        for (i in 0 until 39) {
            list.add("" + i)
        }

        list = list.subList(0,3)
        list.forEach{
            Log.i("linzehao","11  "+it)
        }
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

    var index = 0

    fun click(v: View) {
        when (v.id) {
            R.id.text1 -> {

                LogManager.logClickAction(" " +index ++ )
                LogManager.logClickAction(" " +index ++)
                LogManager.logClickAction(" " +index ++)
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
                for (i in 0 until 10) {
                    LogManager.logStartSlideAction("" + index++)
                    if (i == 9){
                        LogManager.logStopSlideAction("" + index++)
                    }
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
