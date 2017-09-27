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

public interface IBaseRemoteSdk {

    public void init(Application application);

    /**
     * 成功以String形式返回body的内容：用get的方式来请求
     *
     * @param requestParams    请求参数
     * @param responseCallBack 响应回调接口
     */
    public void requestForString(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack);


    /**
     * 成功以File形式返回body的内容：用get的方式来请求
     *
     * @param saveFilePath     要保存的路径--包括文件名
     * @param requestParams    请求参数
     * @param responseCallBack 响应回调接口
     */
    public void requestForFile(String saveFilePath, HttpRequestParams requestParams, IHttpResponseCallBack<File> responseCallBack);


    public void cancelRequest(HttpRequestParams params);

    public void cancelAllRequest();
}
