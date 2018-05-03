package com.raqust.bluko.module.Recycler

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.raqust.bluko.module.log.LogManager

/**
 * Created by linzehao
 * time: 2018/5/3.
 * info:
 */

class ReadLogManage {

    var LogIdleCallback: (() -> Unit)? = null

    var curLastVisibleView = 0
    var curFirstVisibleView = 0

    fun addLogListten(view: RecyclerView, manager: LinearLayoutManager, logIdleCallback: ((Int) -> Unit)
                      , itemInCallback: ((Int) -> Unit), itemOutCallback: ((Int) -> Unit)) {
        view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //停止的时候上传日志
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    logIdleView(view, logIdleCallback)
                    LogManager.logStopSlideAction()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //第一次进来的时候上传日志
                if (view.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                    logIdleView(view, logIdleCallback)
                } else {
                    val lastVisibleView = manager.findLastVisibleItemPosition()
                    val firstVisibleView = manager.findFirstVisibleItemPosition()
                    if (dy > 0) {////向下滚动
                        if (curLastVisibleView != lastVisibleView) {
                            //Log.i("linzehao", "向上显示 $curLastVisibleView  last  $lastVisibleView")
//                            mScrollListData[lastVisibleView].startTime = System.currentTimeMillis()
                            itemInCallback.invoke(lastVisibleView)
                        }
                        if (curFirstVisibleView != firstVisibleView) {
                            //Log.i("linzehao", "向上消失 $curFirstVisibleView  last  $firstVisibleView")
                            logScrollView(curFirstVisibleView, itemOutCallback)
                        }
                    } else {
                        if (curFirstVisibleView != firstVisibleView) {
                            //Log.i("linzehao", "向下显示  $curFirstVisibleView   last  $firstVisibleView")
//                            mScrollListData[firstVisibleView].startTime = System.currentTimeMillis()
                            itemInCallback.invoke(firstVisibleView)
                        }

                        if (curLastVisibleView != lastVisibleView) {
                            //Log.i("linzehao", "向下消失  $curLastVisibleView   last  $lastVisibleView")
                            logScrollView(curLastVisibleView, itemOutCallback)
                        }
                    }
                    curLastVisibleView = lastVisibleView
                    curFirstVisibleView = firstVisibleView
                }
            }
        })
    }


    /**
     * 静止统计
     */
    private fun logIdleView(view: RecyclerView, logIdleCallback: ((Int) -> Unit)) {
        (0 until view.childCount).forEach {
            val index = view.getChildAdapterPosition(view.getChildAt(it))
//            if (!mScrollListData[index].isLog) {
////                Log.i("linzehao", "静止统计  " + mScrollListData[index].content )
//                LogManager.logStartSlideAction("静止统计  " + mScrollListData[index].content)
//            }
//            mScrollListData[index].isLog = true
            logIdleCallback.invoke(index)
        }
    }

    /**
     * 滑动统计
     */
    private fun logScrollView(index: Int, logOutCallback: ((Int) -> Unit)) {
//        if (!mScrollListData[index].isLog && 2000 < (System.currentTimeMillis() - mScrollListData[index].startTime)) {
////            Log.i("linzehao", "消失  " + mListData[index] + "  " + (System.currentTimeMillis() - mScrollListData[index].startTime))
//            LogManager.logStartSlideAction("消失  " + mListData[index] + "  " + (System.currentTimeMillis() - mScrollListData[index].startTime))
//        }
//        mScrollListData[index].isLog = false
//        mScrollListData[index].startTime = 0L
        logOutCallback.invoke(index)
    }

}

