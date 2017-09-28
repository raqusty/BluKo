package com.raqust.bluko.common.net.CallBack;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created on 2017/9/27.
 * Introduce :  string 数据回调
 * Author : zehao
 */
public abstract class JsonStringResponseCallBack<T> extends IJsonBaseCallBack {

    private Class<T> classFile;

    public JsonStringResponseCallBack(Class<T> classFile) {
        this.classFile = classFile;
    }


    /**
     * Http请求成功的回调方法
     *
     * @param code           后台返回码
     * @param msg            成功信息
     * @param responseResult 请求结果字符串，这个仅仅是成功之后的body部分，要不是String，要不就是File
     */
    public abstract void onSuccess(String code, String msg, String data, T responseResult);

    public void preSuccess(String statusCode, String msg, String data) {
        T object = null;
        if (!TextUtils.isEmpty(data)) {
            Gson gson = new Gson();
            object = gson.fromJson(data, classFile);
        }
        onSuccess(statusCode, msg, data, object);

    }
}
