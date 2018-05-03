package com.raqust.bluko.module.Recycler

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.module.ScrollToolActivity.itemAdapter
import com.raqust.bluko.module.log.LogManager
import kotlinx.android.synthetic.main.activity_recyclerview.*
import java.util.*

/**
 * Created by linzehao
 * time: 2018/4/28.
 * info:
 *
 *
 */
class RecyclerViewActivity : BaseActivity() {

    private var mAdapter: itemAdapter? = null
    private val mListData = ArrayList<String>()
    private val mScrollListData = ArrayList<LogScrollEntity>()
    private val magager by lazy { LinearLayoutManager(this, LinearLayout.VERTICAL, false) }

    override fun initViews() {
        (0..59).mapTo(mListData) { "" + it + " " + it }
        (0..59).mapTo(mScrollListData) { LogScrollEntity(System.currentTimeMillis(), false, 1, "" + it + " " + it) }

        commond_recycler.layoutManager = magager
        mAdapter = itemAdapter(mListData, mContext)
        commond_recycler.adapter = mAdapter
    }

    override fun getToolBarResId(): Int = 0


    override fun setListener() {
        ReadLogManage().addLogListten(commond_recycler, magager, {
            if (!mScrollListData[it].isLog) {
                //Log.i("linzehao", "静止统计  " + mScrollListData[index].content )
                LogManager.logStartSlideAction("静止统计  " + mScrollListData[it].content)
            }
            mScrollListData[it].isLog = true
        }, {
            mScrollListData[it].startTime = System.currentTimeMillis()
        }, {
            if (!mScrollListData[it].isLog && 1000 < (System.currentTimeMillis() - mScrollListData[it].startTime)) {
                //Log.i("linzehao", "消失  " + mListData[index] + "  " + (System.currentTimeMillis() - mScrollListData[index].startTime))
                LogManager.logStartSlideAction("消失  " + mListData[it] + "  " + (System.currentTimeMillis() - mScrollListData[it].startTime))
            }
            mScrollListData[it].isLog = false
            mScrollListData[it].startTime = 0L
        })
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId(): Int = R.layout.activity_recyclerview

}