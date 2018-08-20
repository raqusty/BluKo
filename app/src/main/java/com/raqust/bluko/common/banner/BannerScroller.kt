package com.raqust.bluko.common.banner

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller

/**
 * Created by linzehao
 * time: 2018/8/20.
 * info:
 */
class BannerScroller : Scroller {
    private var mDuration = BannerConfig.DURATION

    constructor(context: Context?) : super(context) {}

    constructor(context: Context?, interpolator: Interpolator) : super(context, interpolator) {}

    constructor(context: Context?, interpolator: Interpolator, flywheel: Boolean) : super(context, interpolator, flywheel) {}

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    fun setDuration(time: Int) {
        mDuration = time
    }

}