package com.raqust.bluko.common.net.Impl;

import android.app.Application;

import com.raqust.bluko.common.net.CallBack.IHttpResponseCallBack;
import com.raqust.bluko.common.net.Params.HttpRequestParams;

/**
 * Created on 2017/9/27.
 * Introduce :
 * Author : zehao
 */

public class RemoteNetUtil {

    //BaseRemoteSdkUImpl对象
    private static IBaseRemoteSdk baseRemoteSdkImpl = new BaseRemoteSdkUFactory().createIBaseRemoteSdk();

    public static void init(Application application) {
        baseRemoteSdkImpl.init(application);
    }


    /**
     * get 成功以String形式返回body的内容
     *
     * @param requestParams    请求参数
     * @param responseCallBack 响应回调接口
     */
    public static void requestGetString(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {
        baseRemoteSdkImpl.requestGetString(requestParams, responseCallBack);
    }


    /**
     * post 成功以String形式返回body的内容
     *
     * @param requestParams    请求参数
     * @param responseCallBack 响应回调接口
     */
    public static void requestPostString(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {
        baseRemoteSdkImpl.requestPostString(requestParams, responseCallBack);
    }

    /**
     * 成功以File形式返回body的内容
     *
     * @param requestParams    请求参数
     * @param responseCallBack 响应回调接口
     */
    public static void requestPostFile( HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {
        baseRemoteSdkImpl.requestPostFile( requestParams, responseCallBack);
    }


    public static void cancelRequest(HttpRequestParams params) {
        baseRemoteSdkImpl.cancelRequest(params);
    }

    public static void cancelAllRequest() {
        baseRemoteSdkImpl.cancelAllRequest();
    }


}
