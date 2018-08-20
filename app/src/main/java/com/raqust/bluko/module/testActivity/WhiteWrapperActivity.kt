package com.raqust.bluko.module.testActivity

import android.util.Log
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.wrapper.Constant.COMMAND_START_YOURSELF
import com.raqust.bluko.common.wrapper.Constant.COMMAND_WHITE_LIST
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import com.raqust.bluko.common.wrapper1.WhiteIntentWrapper1
import kotlinx.android.synthetic.main.activity_two_text.*
import com.raqust.bluko.MyApplication
import android.content.ComponentName
import cn.jpush.android.e.a.b.showToast
import android.content.pm.PackageManager





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
            val pm = packageManager
            val permission = PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.RECORD_AUDIO", "packageName")
            if (permission) {
                Log.i("linzehao","test_image" )
            } else {
                Log.i("linzehao","222")
            }
//            WhiteIntentWrapper.whiteListMatters(this, "test", arrayListOf(COMMAND_START_YOURSELF))
        }
        two.setOnClickListener {
            WhiteIntentWrapper.whiteListMatters(this, "test", arrayListOf(COMMAND_START_YOURSELF,COMMAND_WHITE_LIST))

            WhiteIntentWrapper.getWhiteListState(this,arrayListOf(COMMAND_START_YOURSELF,COMMAND_WHITE_LIST))?.forEach {
                Log.i("linzehao",it.first )
            }
        }
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId() = R.layout.activity_two_text

}