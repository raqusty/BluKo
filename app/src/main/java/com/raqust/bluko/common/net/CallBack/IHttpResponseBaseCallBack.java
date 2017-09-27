package com.raqust.bluko.common.net.CallBack;

public abstract class IHttpResponseBaseCallBack {

    /**
     * 取消请求回调的方法
     *
     * @param reason
     */
    public void onCancelled(String reason) {

    }

    /**
     * 结束一次请求
     *
     */
    public abstract void onFinished();

    ;

    /**
     * 网络请求之前调用
     *
     */
    public void onWaiting() {
    }

    /**
     * 网络请求开始之后调用
     *
     */
    public void onStarted() {
    }

    /**
     * 下载的时候不断回调的方法
     *
     * @param total
     * @param current
     * @param isDownloading
     */
    public void onLoading(long total, long current, boolean isDownloading) {

    }
}
