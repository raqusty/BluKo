package com.raqust.bluko.module.smart

import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.module.ScrollToolActivity.itemAdapter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.activity_smart.*
import java.util.ArrayList

/**
 * Created by linzehao
 * time: 2018/4/14.
 * info:activity_smart
 */
class SmartActivity : BaseActivity() {

    private var mOffset = 0
    private var mScrollY = 0

     val list: MutableList<String> = ArrayList()

     val adapter by lazy { itemAdapter(list, this) }

    val manager: LinearLayoutManager  by lazy {  LinearLayoutManager(this, LinearLayout.VERTICAL, false) }

    override fun initViews() {
        for (i in 0..19) {
            list.add("" + i)
        }
        recyclerview.layoutManager = manager
        recyclerview.adapter = adapter
        recyclerview.setNestedScrollingEnabled(false);
    }


    override fun setListener() {
        refreshLayout.setOnMultiPurposeListener(object : SimpleMultiPurposeListener() {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshLayout.finishRefresh(3000)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                refreshLayout.finishLoadMore(2000)
            }

            override fun onHeaderMoving(header: RefreshHeader?, isDragging: Boolean, percent: Float, offset: Int, headerHeight: Int, maxDragHeight: Int) {
                Log.i("linzehao","offset  "+offset)
                mOffset = offset / 2
                parallax.translationY = (mOffset - mScrollY).toFloat()
                toolbar.alpha = 1 - Math.min(percent, 1f)
            }
        })

        scrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            private var lastScrollY = 0
            private val h = DensityUtil.dp2px(170f)
            private val color = ContextCompat.getColor(applicationContext, R.color.colorPrimary) and 0x00ffffff
            override fun onScrollChange(v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                var scrollY = scrollY
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY)
                    mScrollY = if (scrollY > h) h else scrollY
                    buttonBarLayout.setAlpha(1f * mScrollY / h)
                    toolbar.setBackgroundColor(255 * mScrollY / h shl 24 or color)
                    parallax.translationY = (mOffset - mScrollY).toFloat()
                }
                lastScrollY = scrollY
            }
        })
        buttonBarLayout.setAlpha(0f)
        toolbar.setBackgroundColor(0)
    }

    override fun getToolBarResId(): Int {
        return 0
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager) {

    }

    override fun getContentViewResId(): Int {
        return R.layout.activity_smart
    }

}
