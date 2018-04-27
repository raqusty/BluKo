package com.raqust.bluko.module.lazyFragment

import android.support.v4.app.FragmentPagerAdapter
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.BaseFragment
import com.raqust.bluko.common.activity.ToolBarManager
import kotlinx.android.synthetic.main.activity_lazy.*

/**
 * Created by linzehao
 * time: 2018/3/15.
 * info:
 */
class LazyActivity :BaseActivity(){

    private val fragments by lazy { mutableListOf<BaseFragment>() }

    init {
        fragments.add(LazyFragment1())
        fragments.add(LazyFragment2())
        fragments.add(LazyFragment3())
        fragments.add(LazyFragment4())
    }

    override fun initViews() {
        pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount() = fragments.size
            override fun getItem(position: Int) = fragments[position]
            override fun getPageTitle(position: Int) = ""
        }
        pager.offscreenPageLimit = 1
    }

    override fun getToolBarResId(): Int =0

    override fun setListener() {
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId(): Int = R.layout.activity_lazy
}