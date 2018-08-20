package com.raqust.bluko.common.banner

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.raqust.bluko.R
import com.raqust.bluko.common.banner.listener.OnBannerListener
import com.raqust.bluko.common.banner.loader.ImageLoader

/**
 * Created by linzehao
 * time: 2018/8/20.
 * info:
 */


class Banner : FrameLayout, ViewPager.OnPageChangeListener {
    private val tag = "Banner"

    private var delayTime = BannerConfig.TIME
    private var scrollTime = BannerConfig.DURATION
    private var isScroll = BannerConfig.IS_SCROLL
    private var isAutoPlay = BannerConfig.IS_AUTO_PLAY

    private var mContext: Context? = null
    private var mViewPager: BannerViewPager? = null
    private var mIndicatorText: TextView? = null
    private var mScroller: BannerScroller? = null
    private var listener: OnBannerListener? = null
    private val imageViews = mutableListOf<View>()
    private var imageUrls: MutableList<*>? = null
    private val adapter by lazy { BannerPagerAdapter() }
    private var imageLoader: ImageLoader? = null
    private var mOnPageChangeListener: ViewPager.OnPageChangeListener? = null
    private val handler = WeakHandler()

    private var currentItem: Int = 1
    private var count = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        this.mContext = context
        initView()
    }

    private fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_banner, this, true)
        mViewPager = view.findViewById(R.id.bannerViewPager) as BannerViewPager
        mIndicatorText = view.findViewById(R.id.indicator_text) as TextView
        initViewPagerScroll()
    }

    private fun initViewPagerScroll() {
        try {
            val mField = ViewPager::class.java.getDeclaredField("mScroller")
            mField.isAccessible = true
            mScroller = BannerScroller(mViewPager?.context)
            mScroller?.duration = scrollTime
            mField.set(mViewPager, mScroller)
        } catch (e: Exception) {
            Log.e(tag, e.message)
        }
    }

    fun start(): Banner {
        setImageList(imageUrls)
        setData()
        return this
    }

    private fun setData() {
        currentItem = 1
        mViewPager?.addOnPageChangeListener(this)
        mViewPager?.adapter = adapter
        mViewPager?.isFocusable = true
        mViewPager?.currentItem = 1
        if (isScroll && count > 1) {
            mViewPager?.setScrollable(true)
        } else {
            mViewPager?.setScrollable(false)
        }
        if (isAutoPlay)
            startAutoPlay()
    }

    fun startAutoPlay() {
        handler.removeCallbacks(task)
        handler.postDelayed(task, delayTime.toLong())
    }

    fun stopAutoPlay() {
        handler.removeCallbacks(task)
    }

    private fun setImageList(imagesUrl: List<*>?) {
        if (imagesUrl == null || imagesUrl.isEmpty()) {
            Log.e(tag, "The image data set is empty.")
            return
        }
        initIndicator()
        for (i in 0..count + 1) {
            var imageView: View? = null
            if (imageLoader != null) {
                imageView = imageLoader!!.createImageView(context)
            }
            if (imageView == null) {
                imageView = ImageView(context)
            }
            var url: Any? = null
            if (i == 0) {
                url = imagesUrl[count - 1]
            } else if (i == count + 1) {
                url = imagesUrl[0]
            } else {
                url = imagesUrl[i - 1]
            }
            imageViews.add(imageView)
            if (imageLoader != null)
                imageLoader!!.displayImage(context, url!!, imageView as ImageView)
            else
                Log.e(tag, "Please set images loader.")
        }
    }

    private fun initIndicator() {
        imageViews.clear()
        mIndicatorText?.text = "1/" + count
    }

    private val task = object : Runnable {
        override fun run() {
            if (count > 1 && isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1
                //                Log.i(tag, "curr:" + currentItem + " count:" + count);
                if (currentItem == 1) {
                    mViewPager?.setCurrentItem(currentItem, false)
                    handler.post(this)
                } else {
                    mViewPager?.currentItem = currentItem
                    handler.postDelayed(this, delayTime.toLong())
                }
            }
        }
    }

    internal inner class BannerPagerAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return imageViews.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(imageViews.get(position))
            val view = imageViews.get(position)
            if (listener != null) {
                view.setOnClickListener(OnClickListener { listener?.onBannerClick(toRealPosition(position)) })
            }
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

    }

    fun setImageLoader(imageLoader: ImageLoader): Banner {
        this.imageLoader = imageLoader
        return this
    }

    /**
     * banner图片点击事件
     */
    fun setOnBannerListener(listener: OnBannerListener): Banner {
        this.listener = listener
        return this
    }

    /**
     * 设置图片的链接
     */
    fun setImages(imageUrls: MutableList<*>): Banner {
        this.imageUrls = imageUrls
        this.count = imageUrls.size
        return this
    }

    /**
     * 设置可否点击
     */
    fun setViewPagerIsScroll(isScroll: Boolean): Banner {
        this.isScroll = isScroll
        return this
    }

    /**
     * 设置是否自动播放
     */
    fun isAutoPlay(isAutoPlay: Boolean): Banner {
        this.isAutoPlay = isAutoPlay
        return this
    }

    /**
     * 设置延时的时间
     */
    fun setDelayTime(delayTime: Int): Banner {
        this.delayTime = delayTime
        return this
    }

    override fun onPageScrollStateChanged(state: Int) {

        if (mOnPageChangeListener != null) {
            mOnPageChangeListener?.onPageScrollStateChanged(state)
        }
        when (state) {
            0//No operation
            -> if (currentItem == 0) {
                mViewPager?.setCurrentItem(count, false)
            } else if (currentItem == count + 1) {
                mViewPager?.setCurrentItem(1, false)
            }
            1//start Sliding
            -> if (currentItem == count + 1) {
                mViewPager?.setCurrentItem(1, false)
            } else if (currentItem == 0) {
                mViewPager?.setCurrentItem(count, false)
            }
            2//end Sliding
            -> {
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener?.onPageScrolled(toRealPosition(position), positionOffset, positionOffsetPixels)
        }
    }

    override fun onPageSelected(index: Int) {
        var position = index
        currentItem = position
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener?.onPageSelected(toRealPosition(position))
        }

        if (position == 0) position = count
        if (position > count) position = 1
        mIndicatorText?.text = position.toString() + "/" + count
    }

    /**
     * 返回真实的位置
     *
     * @param position
     * @return 下标从0开始
     */
    fun toRealPosition(position: Int): Int {
        var realPosition = (position - 1) % count
        if (realPosition < 0)
            realPosition += count
        return realPosition
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        //        Log.i(tag, ev.getAction() + "--" + isAutoPlay);
        if (isAutoPlay) {
            val action = ev.action
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL
                    || action == MotionEvent.ACTION_OUTSIDE) {
                startAutoPlay()
            } else if (action == MotionEvent.ACTION_DOWN) {
                stopAutoPlay()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

}