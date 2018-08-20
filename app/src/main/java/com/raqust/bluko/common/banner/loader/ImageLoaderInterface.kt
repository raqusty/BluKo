package com.raqust.bluko.common.banner.loader

import android.content.Context
import android.view.View
import java.io.Serializable

/**
 * Created by linzehao
 * time: 2018/8/20.
 * info:
 */
interface ImageLoaderInterface<T : View> : Serializable {

    fun displayImage(context: Context, path: Any, imageView: T)

    fun createImageView(context: Context): T
}