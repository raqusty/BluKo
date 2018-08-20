package com.raqust.bluko.common.banner.loader

import android.content.Context
import android.widget.ImageView

/**
 * Created by linzehao
 * time: 2018/8/20.
 * info:
 */
abstract class ImageLoader : ImageLoaderInterface<ImageView> {

    override fun createImageView(context: Context): ImageView {
        return ImageView(context)
    }

}
