package com.raqust.bluko.module.main;

/**
 * Created on 2017/9/26.
 * Introduce :
 * Author : zehao
 */

public class ImageEntity {
    private String text;
    private String url;

    public ImageEntity(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }


    public String getUrl() {
        return url;
    }

}
