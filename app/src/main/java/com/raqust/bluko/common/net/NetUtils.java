package com.raqust.bluko.common.net;

import android.app.Application;

import com.raqust.bluko.common.net.CallBack.IHttpResponseCallBack;
import com.raqust.bluko.common.net.CallBack.IJsonBaseCallBack;
import com.raqust.bluko.common.net.CallBack.JsonListResponseCallBack;
import com.raqust.bluko.common.net.CallBack.JsonResponseCallBack;
import com.raqust.bluko.common.net.CallBack.JsonStringResponseCallBack;
import com.raqust.bluko.common.net.Constant.Constant;
import com.raqust.bluko.common.net.Impl.RemoteNetUtil;
import com.raqust.bluko.common.net.Params.HttpRequestParams;
import com.raqust.bluko.common.net.Params.HttpRequestType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 2017/9/27.
 * Introduce :  业务性的封装处理
 * Author : zehao
 */

public class NetUtils {

    public static void init(Application application) {
        RemoteNetUtil.init(application);
    }

    /**
     * get string
     *
     * @param url
     * @param jsonCallback
     */
    public static void requestGetString(String url, IJsonBaseCallBack jsonCallback) {
        requestGetString(url, null, jsonCallback);
    }

    public static void requestGetString(String url, HashMap<String, String> strParams, IJsonBaseCallBack jsonCallback) {
        requestGetString(url, strParams, null, jsonCallback);
    }

    public static void requestGetString(String url, HashMap<String, String> strParams, HashMap<String, String> headParams, IJsonBaseCallBack jsonCallback) {
        HttpRequestParams requestParams = getRequestParams(url, HttpRequestType.GET_STRING);
        requestParams.strParams = strParams;
        requestParams.headParams = headParams;
        RemoteNetUtil.requestGetString(requestParams, dealData(jsonCallback));
    }

    /**
     * post string
     *
     * @param url
     * @param jsonCallback
     */
    public static void requestPostString(String url, IJsonBaseCallBack jsonCallback) {
        requestPostString(url, null, jsonCallback);
    }

    public static void requestPostString(String url, HashMap<String, String> headParams, IJsonBaseCallBack jsonCallback) {
        HttpRequestParams requestParams = getRequestParams(url, HttpRequestType.POST_STRING);
        requestParams.headParams = headParams;
        RemoteNetUtil.requestPostString(requestParams, dealData(jsonCallback));
    }

    /**
     * post 文件
     *
     * @param url
     * @param fileList
     * @param jsonCallback
     */
    public static void requestPostFile(String url, ArrayList<HttpRequestParams.FileInput> fileList, IJsonBaseCallBack jsonCallback) {
        requestPostFile(url, fileList, null, jsonCallback);
    }

    public static void requestPostFile(String url, ArrayList<HttpRequestParams.FileInput> fileList, HashMap<String, String> strParams, IJsonBaseCallBack jsonCallback) {
        requestPostFile(url, fileList, strParams, null, jsonCallback);
    }

    public static void requestPostFile(String url, ArrayList<HttpRequestParams.FileInput> fileList, HashMap<String, String> strParams,
                                       HashMap<String, String> headParams, IJsonBaseCallBack jsonCallback) {
        HttpRequestParams requestParams = getRequestParams(url, HttpRequestType.POST_FORM);
        requestParams.submitFileList = fileList;
        requestParams.strParams = strParams;
        requestParams.headParams = headParams;
        RemoteNetUtil.requestPostFile(requestParams, dealData(jsonCallback));
    }


    public static void cancelRequest(HttpRequestParams params) {
        RemoteNetUtil.cancelRequest(params);
    }

    public static void cancelAllRequest() {
        RemoteNetUtil.cancelAllRequest();
    }

    /*以下是逻辑****************************************************************************************************************************************
     **************************************************************************************************************************************************
     **************************************************************************************************************************************************/

    /**
     * 获取 https请求参数，设置通用参数，比如请求时间
     *
     * @param url
     * @param type
     * @return
     */
    private static HttpRequestParams getRequestParams(String url, HttpRequestType type) {
        return new HttpRequestParams.Builder().setRequestType(type).setConnectTimeout(Constant.CONNEC_TTIME_OUT)
                .setGetTimeout(Constant.RESPOND_TTIME_OUT).setHostUrl(url).build();

    }

    /**
     * 数据回调给model层，进行json处理
     * 处理数据格式，如果有处理code 的返回值
     *
     * @param jsonCallback
     * @return
     */
    private static IHttpResponseCallBack dealData(final IJsonBaseCallBack jsonCallback) {
        IHttpResponseCallBack<String> responseCallBack = new IHttpResponseCallBack<String>() {
            @Override
            public void onSuccess(String responseResult) {
                JSONObject resultJson = null;
                try {
                    resultJson = new JSONObject(responseResult);
                    String statusCode = resultJson.getString(Constant.RESPOND_CODE);
                    String msg = resultJson.getString(Constant.RESPOND_MSG);
                    String data = resultJson.getString(Constant.RESPOND_DATA);
                    if ((Constant.RESPOND_ERROR_SUCCESS + "").equals(statusCode)) {
                        if (jsonCallback instanceof JsonListResponseCallBack) {
                            ((JsonListResponseCallBack) jsonCallback).preSuccess(statusCode, msg, data);
                        } else if (jsonCallback instanceof JsonResponseCallBack) {
                            ((JsonResponseCallBack) jsonCallback).preSuccess(statusCode, msg, data);
                        } else if (jsonCallback instanceof JsonStringResponseCallBack) {
                            ((JsonStringResponseCallBack) jsonCallback).preSuccess(statusCode, msg, data);
                        }
                    } else {
                        jsonCallback.onFail(statusCode + "", msg, data);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    jsonCallback.onFail(Constant.RESPOND_ERROR_ANALYSIS + "", Constant.RESPOND_ERROR_ANALYSIS_MSG, "");

                }
                jsonCallback.onFinished();
            }

            @Override
            public void onFail(int errorCode, String message, String result) {
                jsonCallback.onFail(errorCode + "", message, result);
                jsonCallback.onFinished();

            }

            @Override
            public void onFinished() {
                jsonCallback.onFinished();
            }
        };
        return responseCallBack;

    }

}
