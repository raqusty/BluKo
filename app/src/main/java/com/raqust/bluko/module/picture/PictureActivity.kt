package com.raqust.bluko.module.picture

import android.util.Log
import android.view.View
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.utils.VersionUtil
import java.sql.Date

/**
 * Created by linzehao
 * time: 2018/3/12.
 * info:
 */
class PictureActivity : BaseActivity() {

    val version1="1.0.0"
    val version2="1.1.1"
    val version3="1.1.1.123"
    val version4="2.3.1.33"

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

                Log.i("linzehao", VersionUtil.compareVersion(version1,version2).toString())
                Log.i("linzehao", VersionUtil.compareVersion(version1,version1).toString())
                Log.i("linzehao", VersionUtil.compareVersion(version2,version1).toString())
                Log.i("linzehao", VersionUtil.compareVersion(version1,version3).toString())
                Log.i("linzehao", VersionUtil.compareVersion(version1,version2).toString())
                Log.i("linzehao", VersionUtil.compareVersion(version4,version2).toString())
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
