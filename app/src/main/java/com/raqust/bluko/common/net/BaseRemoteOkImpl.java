package com.raqust.bluko.common.net;

import android.app.Application;

import com.raqust.bluko.common.net.CallBack.IHttpResponseCallBack;
import com.raqust.bluko.common.net.IBaseRemoteSdk;
import com.raqust.bluko.common.net.Params.HttpRequestParams;

import java.io.File;

/**
 * Created on 2017/9/27.
 * Introduce :
 * Author : zehao
 */

public class BaseRemoteOkImpl  implements IBaseRemoteSdk {
    @Override
    public void init(Application application) {

    }

    @Override
    public void requestForString(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {

    }

    @Override
    public void requestForFile(String saveFilePath, HttpRequestParams requestParams, IHttpResponseCallBack<File> responseCallBack) {

    }

    @Override
    public void cancelRequest(HttpRequestParams params) {

    }

    @Override
    public void cancelAllRequest() {

    }
}
