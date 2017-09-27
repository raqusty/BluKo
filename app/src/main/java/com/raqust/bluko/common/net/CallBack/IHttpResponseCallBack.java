package com.raqust.bluko.common.net.CallBack;

/**
 * Created on 2017/9/27.
 * Introduce :
 * Author : zehao
 */
public abstract class IHttpResponseCallBack<T> extends IHttpResponseBaseCallBack {


    /**
     * Http请求成功的回调方法
     *
     * @param responseResult 请求结果字符串，这个仅仅是成功之后的body部分，要不是String，要不就是File
     */
    public abstract void onSuccess(T responseResult);

    /**
     * Http请求失败的回调方法
     *
     * @param errorCode
     * @param message        失败原因
     * @param result         失败内容
     */
    public abstract void onFail(int errorCode, String message, String result);
}
