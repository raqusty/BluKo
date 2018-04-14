package com.raqust.bluko.module.smart

import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.scwang.smartrefresh.header.BezierCircleHeader
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.header.PhoenixHeader
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.activity_smart.*

/**
 * Created by linzehao
 * time: 2018/4/14.
 * info:activity_smart
 */
class SmartActivity : BaseActivity() {

    private var mOffset = 0
    private var mScrollY = 0

    override fun initViews() {
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
                mOffset = offset / 2
                parallax.translationY = (mOffset - mScrollY).toFloat()
                toolbar.alpha = 1 - Math.min(percent, 1f)
            }
            //            @Override
            //            public void onHeaderPulling(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
            //                mOffset = offset / 2;
            //                parallax.setTranslationY(mOffset - mScrollY);
            //                toolbar.setAlpha(1 - Math.min(percent, 1));
            //            }
            //            @Override
            //            public void onHeaderReleasing(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
            //                mOffset = offset / 2;
            //                parallax.setTranslationY(mOffset - mScrollY);
            //                toolbar.setAlpha(1 - Math.min(percent, 1));
            //            }
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
