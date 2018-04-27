package com.raqust.bluko.module.wanbeiActivity

import android.app.Activity
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.utils.VersionUtil
import com.raqust.bluko.common.wrapper.WhiteIntentWrapper
import kotlinx.android.synthetic.main.activity_invite.*
import java.util.*

/**
 * Created by linzehao
 * time: 2018/4/2.
 * info: : BaseActivity()
 */
class InviteActivity : BaseActivity() {
    var layerDrawable: LayerDrawable? = null


    val inflater by lazy { LayoutInflater.from(mContext) }

    override fun initViews() {
        for (i in 0 until 10) {
            flexboxLayout.addView(getText("" + i + i))
        }

        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = ContextCompat.getDrawable(this, R.color.colorAccent)
        layers[1] = ContextCompat.getDrawable(this, R.mipmap.nav_back_btn)
        layerDrawable = LayerDrawable(layers)
        image.setImageDrawable(layerDrawable)

//        WhiteIntentWrapper.whiteListMatters(this, "测试")

        val v1 = "1.0.2.33"
        val v2 = "1.0.2.444"
        val v3 = "1.0.2.1"
        val v4 = "1.0.2"
        val v5 = "1.0.5"
        val v6 = "1.0.66.32"

        Log.i("linzehao","a   "+VersionUtil.compareVersion(v1,v2))
        Log.i("linzehao","b   "+VersionUtil.compareVersion(v2,v1))
        Log.i("linzehao","c   "+VersionUtil.compareVersion(v1,v3))
        Log.i("linzehao","d   "+VersionUtil.compareVersion(v1,v4))
        Log.i("linzehao","e   "+VersionUtil.compareVersion(v1,v5))
        Log.i("linzehao","f   "+VersionUtil.compareVersion(v1,v6))

        Log.i("linzehao","a   "+VersionUtil.duibi(v1,v2))
        Log.i("linzehao","b   "+VersionUtil.duibi(v2,v1))
        Log.i("linzehao","c   "+VersionUtil.duibi(v1,v3))
        Log.i("linzehao","d   "+VersionUtil.duibi(v1,v4))
        Log.i("linzehao","e   "+VersionUtil.duibi(v1,v5))
        Log.i("linzehao","f   "+VersionUtil.duibi(v1,v6))

    }


    private fun getText(value: String): View {
        val rootView = inflater.inflate(R.layout.item_tag_property, null) as View
        val textView = rootView.findViewById(R.id.textview) as TextView
        textView.text = value
        textView.setOnClickListener {
            textView.isSelected = !textView.isSelected
        }
        return rootView
    }

    override fun getToolBarResId() = 0

    override fun setListener() {
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {
    }

    override fun getContentViewResId() = R.layout.activity_invite

    fun click(v: View) {
        val layers = arrayOfNulls<Drawable>(2)

        val draw = ContextCompat.getDrawable(this, R.color.colorPrimaryDark)
        layers[0] = ContextCompat.getDrawable(this, R.color.colorPrimaryDark)
        layers[1] = ContextCompat.getDrawable(this, R.mipmap.nav_back_btn)
        layerDrawable = LayerDrawable(layers)
        image.setImageDrawable(layerDrawable)

        v.isSelected = !v.isSelected
    }
}