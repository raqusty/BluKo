package com.raqust.bluko.common.utils.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * Created on 2017/9/25.
 * Introduce :
 * Author : zehao
 */
public class ImageImpl implements IImageSdk {
    @Override
    public void initImageSDK(Context context) {

    }

    @Override
    public void displayLoacalImage(Context context, Integer drawableResId, String imgPath, ImageView mImageView, boolean isCenterCrop, Integer loadingImgResID, float compressionRatio) {

    }

    @Override
    public Bitmap getBitmapFromCache(int imageSource, String url) {
        return null;
    }

    @Override
    public void clearMemoryCache(int imageSource, String url) {

    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public File getFileFromDiskCache(int imageSource, String url) {
        return null;
    }

    @Override
    public void clearDiskCache(int imageSource, String url) {

    }

    @Override
    public void clearDiskCache() {

    }
}