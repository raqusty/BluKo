package com.raqust.bluko.module.Recycler

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.module.ScrollToolActivity.itemAdapter
import kotlinx.android.synthetic.main.activity_recyclerview.*
import java.util.*

/**
 * Created by linzehao
 * time: 2018/4/28.
 * info:
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

    var curLastVisibleView = 0
    var curFirstVisibleView = 0

    override fun setListener() {
        commond_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //停止的时候上传日志
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    logIdleView()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //第一次进来的时候上传日志
                if (commond_recycler.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    logIdleView()
                } else {
                    val lastVisibleView = magager.findLastVisibleItemPosition()
                    val firstVisibleView = magager.findFirstVisibleItemPosition()
                    if (dy > 0) {////向下滚动
                        if (curLastVisibleView != lastVisibleView) {
                            //Log.i("linzehao", "向上显示 $curLastVisibleView  last  $lastVisibleView")
                            mScrollListData[lastVisibleView].startTime = System.currentTimeMillis()
                        }
                        if (curFirstVisibleView != firstVisibleView) {
                            //Log.i("linzehao", "向上显示 $curFirstVisibleView  last  $firstVisibleView")
                            if (2000 < (System.currentTimeMillis() - mScrollListData[curFirstVisibleView].startTime)) {
                                Log.i("linzehao", "向上消失  " + mListData[curFirstVisibleView] + "  " + (System.currentTimeMillis() - mScrollListData[curFirstVisibleView].startTime))
                            }

                        }
                    } else {
                        if (curFirstVisibleView != firstVisibleView) {
                            //Log.i("linzehao", "向下显示  $curFirstVisibleView   last  $firstVisibleView")
                            mScrollListData[firstVisibleView].startTime = System.currentTimeMillis()
                        }

                        if (curLastVisibleView != lastVisibleView) {
                            //Log.i("linzehao", "向下消失  $curLastVisibleView   last  $lastVisibleView")
                            if (2000 < (System.currentTimeMillis() - mScrollListData[curLastVisibleView].startTime)) {
                                Log.i("linzehao", "向下消失  " + mListData[curLastVisibleView] + "  " + (System.currentTimeMillis() - mScrollListData[curLastVisibleView].startTime))
                            }
                        }
                    }
                    curLastVisibleView = lastVisibleView
                    curFirstVisibleView = firstVisibleView
                }
            }

        })
    }

    private fun logIdleView() {
        Log.i("linzehao", "SCROLL_STATE_IDLE  " + commond_recycler.childCount)
        (0 until commond_recycler.childCount).forEach {
            Log.i("linzehao", "111  " + it)
            val index = commond_recycler.getChildAdapterPosition(commond_recycler.getChildAt(it))
            Log.i("linzehao", "222   " + index)
            mScrollListData[index].isLog = true
        }

    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId(): Int = R.layout.activity_recyclerview

}