package com.raqust.bluko.common.net;

import android.app.Application;

import com.raqust.bluko.common.net.CallBack.IHttpResponseCallBack;
import com.raqust.bluko.common.net.Params.HttpRequestParams;

import java.io.File;

/**
 * Created on 2017/9/27.
 * Introduce :  业务性的封装处理
 * Author : zehao
 */

public class NetUtils {

    public static void init(Application application) {
        RemoteNetUtil.init(application);
    }

    public static void requestForFile(String saveFilePath, HttpRequestParams requestParams, IHttpResponseCallBack<File> responseCallBack) {
        RemoteNetUtil.requestForFile(saveFilePath, requestParams, responseCallBack);
    }


    public static void cancelRequest(HttpRequestParams params) {
        RemoteNetUtil.cancelRequest(params);
    }

    public static void cancelAllRequest() {
        RemoteNetUtil.cancelAllRequest();
    }


}
