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
class LazyFragment3 : BaseFragment(){
    override fun initViews(view: View) {
        Log.i("linzehao","initViews 3")
    }

    override fun setListener() {
    }

    override fun getContentViewResId() = R.layout.fragment_lazy

    override fun firstVisibleInitData() {
        Log.i("linzehao","pullData 3 ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("linzehao","onDestroy 3" )
    }
}