package com.raqust.bluko.module.lazyFragment

import android.util.Log
import android.view.View
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseFragment

/**
 * Created by linzehao
 * time: 2018/3/15.
 * info:
 */
class LazyFragment4 : BaseFragment(){
    override fun initViews(view: View) {
        Log.i("linzehao","initViews 4")
    }

    override fun setListener() {
    }

    override fun getContentViewResId() = R.layout.fragment_lazy2

    override fun firstVisibleInitData() {
        Log.i("linzehao","pullData 4 ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("linzehao","onDestroy 4" )
    }
}