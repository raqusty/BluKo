package com.raqust.bluko.module.testActivity.banner

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.raqust.bluko.common.banner.loader.ImageLoader

/**
 * Created by linzehao
 * time: 2018/8/20.
 * info:
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)


        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        val uri = Uri.parse(path as String)
        imageView?.setImageURI(uri)
    }

//    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
//        //Glide 加载图片简单用法
//        Glide.with(context).load(path).into(imageView)
//
//
//        //用fresco加载图片简单用法，记得要写下面的createImageView方法
//        val uri = Uri.parse(path as String)
//        imageView?.setImageURI(uri)
//    }

}