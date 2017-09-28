package com.raqust.bluko.common.net.CallBack;

public abstract class IJsonBaseCallBack extends IHttpResponseBaseCallBack{

    /**
     * Http请求失败的回调方法
     *
     * @param errorCode
     * @param message   失败原因
     * @param result    失败内容
     */
    public abstract void onFail(String errorCode, String message, String result);

}
