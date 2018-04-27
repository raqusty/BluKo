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
class LazyFragment6 : BaseFragment(){
    var data1 = ""
    override fun initViews(view: View) {
        var text =  view.findViewById(R.id.text) as TextView
        text.text = data1
        Log.i("linzehao", "initViews "+data1)
    }

    fun setData(data:String){
        data1  = data
    }

    override fun setListener() {
    }

    override fun getContentViewResId() = R.layout.fragment_lazy3

    override fun firstVisibleInitData() {
        Log.i("linzehao", "pullData  "+data1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("linzehao","onDestroy "+data1 )
    }
}