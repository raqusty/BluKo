package com.raqust.bluko.common.net;

import android.app.Application;

import com.raqust.bluko.common.net.CallBack.IHttpResponseCallBack;
import com.raqust.bluko.common.net.Impl.RemoteNetUtil;
import com.raqust.bluko.common.net.Params.HttpRequestParams;
import com.raqust.bluko.common.net.Params.HttpRequestType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 2017/9/27.
 * Introduce :  业务性的封装处理
 * Author : zehao
 */

public class NetUtils {
    //链接超时时间
    public static final int CONNEC_TTIME_OUT = 1500;

    //响应超时时间
    public static final int RESPOND_TTIME_OUT = 1500;

    public static void init(Application application) {
        RemoteNetUtil.init(application);
    }


    /**
     * get string
     *
     * @param url
     * @param responseCallBack
     */
    public static void requestGetString(String url, IHttpResponseCallBack<String> responseCallBack) {
        requestGetString(url, null, responseCallBack);
    }

    public static void requestGetString(String url, HashMap<String, String> strParams, IHttpResponseCallBack<String> responseCallBack) {
        requestGetString(url, strParams, null, responseCallBack);
    }

    public static void requestGetString(String url, HashMap<String, String> strParams, HashMap<String, String> headParams, IHttpResponseCallBack<String> responseCallBack) {
        HttpRequestParams requestParams = getRequestParams(url, HttpRequestType.GET_STRING);
        requestParams.strParams = strParams;
        requestParams.headParams = headParams;
        RemoteNetUtil.requestGetString(requestParams, responseCallBack);
    }

    /**
     * post string
     *
     * @param url
     * @param responseCallBack
     */
    public static void requestPostString(String url, IHttpResponseCallBack<String> responseCallBack) {
        requestPostString(url, null, responseCallBack);
    }

    public static void requestPostString(String url, HashMap<String, String> headParams, IHttpResponseCallBack<String> responseCallBack) {
        HttpRequestParams requestParams = getRequestParams(url, HttpRequestType.POST_STRING);
        requestParams.headParams = headParams;
        RemoteNetUtil.requestPostString(requestParams, responseCallBack);
    }

    /**
     * post 文件
     *
     * @param url
     * @param fileList
     * @param responseCallBack
     */
    public static void requestPostFile(String url, ArrayList<HttpRequestParams.FileInput> fileList, IHttpResponseCallBack<String> responseCallBack) {
        requestPostFile(url, fileList, null, responseCallBack);
    }

    public static void requestPostFile(String url, ArrayList<HttpRequestParams.FileInput> fileList, HashMap<String, String> strParams, IHttpResponseCallBack<String> responseCallBack) {
        requestPostFile(url, fileList, strParams, null, responseCallBack);
    }

    public static void requestPostFile(String url, ArrayList<HttpRequestParams.FileInput> fileList, HashMap<String, String> strParams,
                                       HashMap<String, String> headParams, IHttpResponseCallBack<String> responseCallBack) {
        HttpRequestParams requestParams = getRequestParams(url, HttpRequestType.POST_FORM);
        requestParams.submitFileList = fileList;
        requestParams.strParams = strParams;
        requestParams.headParams = headParams;
        RemoteNetUtil.requestPostFile(requestParams, responseCallBack);
    }


    public static void cancelRequest(HttpRequestParams params) {
        RemoteNetUtil.cancelRequest(params);
    }

    public static void cancelAllRequest() {
        RemoteNetUtil.cancelAllRequest();
    }

    private static HttpRequestParams getRequestParams(String url, HttpRequestType type) {
        return new HttpRequestParams.Builder().setRequestType(type).setConnectTimeout(CONNEC_TTIME_OUT).setGetTimeout(RESPOND_TTIME_OUT).setHostUrl(url).build();

    }


}
