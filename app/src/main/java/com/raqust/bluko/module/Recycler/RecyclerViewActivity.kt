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
    private val mListData1 = ArrayList<Boolean>()
    private val mListData2 = ArrayList<Long>()
    private val magager by lazy { LinearLayoutManager(this, LinearLayout.VERTICAL, false) }

    override fun initViews() {
        (0..59).mapTo(mListData) { "" + it + " "  + it }
        (0..59).mapTo(mListData1) { false }
        (0..59).mapTo(mListData2) { System.currentTimeMillis()}
        commond_recycler.layoutManager = magager
        mAdapter = itemAdapter(mListData, mContext)
        commond_recycler.adapter = mAdapter

        for (i in 0 until commond_recycler.childCount) {
            mListData1[i] = true
        }

//        mListData1.forEach{
//             Log.i("linzehao","1 "+it)
//         }
    }

    override fun getToolBarResId(): Int = 0

    var curLastVisibleView = 0
    var curFirstVisibleView = 0

    override fun setListener() {
        commond_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisibleView = magager.findLastVisibleItemPosition()
                val firstVisibleView = magager.findFirstVisibleItemPosition()

//                Log.i("linzehao", "向上 firstVisibleView $firstVisibleView  last  $lastVisibleView")
                if (dy > 0) {////向下滚动
                    if (curLastVisibleView != lastVisibleView) {
//                        Log.i("linzehao", "向上显示 $curLastVisibleView  last  $lastVisibleView")
                        mListData2[lastVisibleView] =  System.currentTimeMillis()
                    }
                    if (curFirstVisibleView != firstVisibleView) {
//                        Log.i("linzehao", "向上显示 $curFirstVisibleView  last  $firstVisibleView")
                        Log.i("linzehao", "向上消失  " +mListData[curFirstVisibleView] +"  " + (System.currentTimeMillis() - mListData2[curFirstVisibleView]))
                    }
                } else {
                    if (curFirstVisibleView != firstVisibleView) {
//                        Log.i("linzehao", "向下显示  $curFirstVisibleView   last  $firstVisibleView")
                        mListData2[firstVisibleView] =  System.currentTimeMillis()
                    }

                    if (curLastVisibleView != lastVisibleView) {
//                        Log.i("linzehao", "向下消失  $curLastVisibleView   last  $lastVisibleView")
                        Log.i("linzehao", "向下消失  "+mListData[curLastVisibleView]  +"  "  + (System.currentTimeMillis()  - mListData2[curLastVisibleView]))
                    }
                }
                curLastVisibleView = lastVisibleView
                curFirstVisibleView = firstVisibleView
            }
        })
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId(): Int = R.layout.activity_recyclerview

}