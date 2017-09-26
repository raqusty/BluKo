package com.raqust.bluko.common.utils.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * Created on 2017/9/26.
 * Introduce :
 * Author : zehao
 */

public interface IImageSdk {


    /**
     * 初始化SDK
     *
     * @param context
     */
    public void initImageSDK(Context context);

    /**
     * 显示本地（手机或内存卡）drawable图片
     *
     * @param context
     * @param drawableResId    drawable图片资源Id
     * @param imgPath          图片全路径
     * @param mImageView       图片的载体ImageView对象
     * @param isCenterCrop     是否设置CenterCrop
     * @param loadingImgResID  在加载期间显示的图片
     * @param compressionRatio 压缩比例
     */
    public void displayLoacalImage(Context context, Integer drawableResId, String imgPath, ImageView mImageView,
                                   boolean isCenterCrop, Integer loadingImgResID, float compressionRatio);


    /**
     * 显示图片---带进度监听
     *
     * @param options                 相关配置
     * @param FadeInDurationMillis    渐变的时间
     * @param imageSouce              图片显示的来源 这个主要是跟下面的url配合用的，假如url已做了来源的区分的话，
     *                                这里就不用做了，可以随便填，但假如url没做区分，这里要填，0：网络 1：contenprovider
     *                                2：应用内assets文件夹  3：应用内drawable文件夹 4：SD卡
     * @param url                     图片的url
     * @param mImageView              显示的控件
     * @param listener                下载图片各个生命周期的监听
     * @param loadingProgressListener 下载进度的监听
     */

//    public void displayImage(ImageSdkOptions options, int FadeInDurationMillis, int imageSouce, String url,
//                             final ImageView mImageView, IImageLoadingListener listener, IImageLoadingProgressListener loadingProgressListener);

    /**
     * 根据图片URI从内存中获取图片的Bitmap实例
     *
     * @param url 图片URI
     * @return
     */
    public Bitmap getBitmapFromCache(int imageSource, String url);

    /**
     * 清除某个URL的内存中的缓存
     */
    public void clearMemoryCache(int imageSource, String url);

    /**
     * 清除内存中所有的缓存
     */
    public void clearMemoryCache();


    /**
     * 根据图片URI从硬盘中获取图片的Bitmap实例
     *
     * @param url 图片URI
     * @return
     */
    public File getFileFromDiskCache(int imageSource, String url);

    /**
     * 清除某个URL的硬盘中的缓存
     */
    public void clearDiskCache(int imageSource, String url);

    /**
     * 清除硬盘中的缓存
     */
    public void clearDiskCache();

}
