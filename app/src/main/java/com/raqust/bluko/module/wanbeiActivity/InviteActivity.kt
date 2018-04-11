package com.raqust.bluko.module.wanbeiActivity

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RotateDrawable
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import kotlinx.android.synthetic.main.activity_invite.*

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
    }

    private fun getText(value: String): View {
        val rootView = inflater.inflate(R.layout.item_tag_property, null)  as View
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