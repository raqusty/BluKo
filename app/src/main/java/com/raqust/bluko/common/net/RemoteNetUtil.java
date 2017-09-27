package com.raqust.bluko.common.net;

import android.app.Application;

import com.raqust.bluko.common.net.CallBack.IHttpResponseCallBack;
import com.raqust.bluko.common.net.Params.HttpRequestParams;

import java.io.File;

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
     * 成功以String形式返回body的内容
     *
     * @param requestParams    请求参数
     * @param responseCallBack 响应回调接口
     */
    public static void requestForString(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {
        baseRemoteSdkImpl.requestForString(requestParams, responseCallBack);
    }


    /**
     * 成功以File形式返回body的内容
     *
     * @param saveFilePath     要保存的路径--包括文件名
     * @param requestParams    请求参数
     * @param responseCallBack 响应回调接口
     */
    public static void requestForFile(String saveFilePath, HttpRequestParams requestParams, IHttpResponseCallBack<File> responseCallBack) {
        baseRemoteSdkImpl.requestForFile(saveFilePath, requestParams, responseCallBack);
    }


    public static void cancelRequest(HttpRequestParams params) {
        baseRemoteSdkImpl.cancelRequest(params);
    }

    public static void cancelAllRequest() {
        baseRemoteSdkImpl.cancelAllRequest();
    }


}
