package com.raqust.bluko.module.lazyFragment

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseFragment

/**
 * Created by linzehao
 * time: 2018/3/15.
 * info:
 */
class LazyFragment1 : BaseFragment(){

    private val fragments by lazy { mutableListOf<LazyFragment6>() }

    init {
        fragments.add(LazyFragment6())
        fragments.add(LazyFragment6())
        fragments.add(LazyFragment6())
        fragments.add(LazyFragment6())
        fragments[0].setData("00")
        fragments[1].setData("11")
        fragments[2].setData("22")
        fragments[3].setData("33")
    }

    public override fun setArguments(args: Bundle?) {
        super.setArguments(args)
    }


    override fun setListener() {
    }

    override fun getContentViewResId() = R.layout.activity_lazy2

    override fun firstVisibleInitData() {
        Log.i("linzehao","pullData 1 ")
    }

    override fun initViews(view: View) {
        Log.i("linzehao", "initViews 1")
        var pager2 = view.findViewById(R.id.pager2) as ViewPager
        pager2.adapter = object : FragmentPagerAdapter(activity.supportFragmentManager) {
            override fun getCount() = fragments.size
            override fun getItem(position: Int) = fragments[position]
            override fun getPageTitle(position: Int) = ""
        }
        pager2.offscreenPageLimit = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
         Log.i("linzehao","onDestroy 1" )
    }

}