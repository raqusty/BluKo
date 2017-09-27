package com.raqust.bluko.common.net.CallBack;

public abstract class IHttpResponseBaseCallBack {

    /**
     * 取消请求回调的方法
     *
     * @param reason
     * @param originalObject
     */
    public void onCancelled(String reason, Object originalObject) {

    }

    /**
     * 结束一次请求
     *
     * @param originalObject
     */
    public abstract void onFinished(Object originalObject);

    ;

    /**
     * 网络请求之前调用
     *
     * @param originalObject
     */
    public void onWaiting(Object originalObject) {
    }

    /**
     * 网络请求开始之后调用
     *
     * @param originalObject
     */
    public void onStarted(Object originalObject) {
    }

    /**
     * 下载的时候不断回调的方法
     *
     * @param total
     * @param current
     * @param isDownloading
     * @param originalObject
     */
    public void onLoading(long total, long current, boolean isDownloading, Object originalObject) {

    }
}
