package com.raqust.bluko.module.lazyFragment

import android.util.Log
import android.view.View
import android.widget.TextView
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseFragment

/**
 * Created by linzehao
 * time: 2018/3/15.
 * info:
 */
class LazyFragment2 : BaseFragment() {

    override fun initViews(view: View) {
        Log.i("linzehao", "2")
        var text = view.findViewById(R.id.text111) as TextView
        text.text = "123123"
    }

    override fun setListener() {
    }

    override fun getContentViewResId() = R.layout.fragment_lazy2

    override fun firstVisibleInitData() {
        Log.i("linzehao", "2 pullData")
    }
}