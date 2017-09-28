package com.raqust.bluko.common.net.Impl;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.raqust.bluko.common.net.CallBack.IHttpResponseBaseCallBack;
import com.raqust.bluko.common.net.CallBack.IHttpResponseCallBack;
import com.raqust.bluko.common.net.Constant.Constant;
import com.raqust.bluko.common.net.Params.HttpRequestParams;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created on 2017/9/27.
 * Introduce :  做了post  get  postFrom  其他的方式没做，有需要再做
 * 资料 https://github.com/hongyangAndroid/okhttputils
 * Author : zehao
 */

public class BaseRemoteOkImpl implements IBaseRemoteSdk {
    static final String TAG = "BaseRemoteOkImpl";


    @Override
    public void init(Application application) {
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(application));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

       /* 以下是 https的设置，但没得测试，所以先不用******************************
        双向认证
        HttpsUtils.getSslSocketFactory(
                证书的inputstream,
                本地证书的inputstream,
                本地证书的密码)
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(证书的inputstream, null, null);
        OkHttpClient okHttpClient1 = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient1);
        */
    }

    @Override
    public void requestGetString(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {
        if (!checkParams(requestParams, responseCallBack))
            return;

        GetBuilder builder = OkHttpUtils.get().url(requestParams.hostUrl);
        setBuilderParams(requestParams, builder);
        setRequestCallback(builder, responseCallBack, requestParams);
    }

    @Override
    public void requestPostString(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {
        if (!checkParams(requestParams, responseCallBack))
            return;

        PostStringBuilder builder = OkHttpUtils.postString().url(requestParams.hostUrl);
        setBuilderParams(requestParams, builder);
        setRequestCallback(builder, responseCallBack, requestParams);
    }

    @Override
    public void requestPostFile(HttpRequestParams requestParams, IHttpResponseCallBack<String> responseCallBack) {
        if (!checkParams(requestParams, responseCallBack))
            return;

        PostFormBuilder builder = OkHttpUtils.post().url(requestParams.hostUrl);
        setBuilderParams(requestParams, builder);
        setRequestCallback(builder, responseCallBack, requestParams);
    }

    @Override
    public void cancelRequest(HttpRequestParams params) {
        OkHttpUtils.getInstance().cancelTag(params.hostUrl);
    }

    @Override
    public void cancelAllRequest() {

    }

    //以下是逻辑*****************************//

    private boolean checkParams(HttpRequestParams requestParams, IHttpResponseBaseCallBack responseCallBack) {
        if (requestParams == null) {
            Log.i(TAG, "requestParams = null");
            return false;
        }
        if (responseCallBack == null) {
            Log.i(TAG, "responseCallBack = null");
            return false;
        }
        if (TextUtils.isEmpty(requestParams.hostUrl)) {
            Log.i(TAG, "hostUrl = null");
            return false;
        }
        return true;
    }

    /**
     * 设置 http 的参数  各种参数
     *
     * @param requestParams
     * @param builder
     * @return
     */
    private OkHttpRequestBuilder setBuilderParams(HttpRequestParams requestParams, OkHttpRequestBuilder builder) {
        if (requestParams.headParams != null) {
            for (String key : requestParams.headParams.keySet()) {
                builder.addHeader(key, requestParams.headParams.get(key));
            }
        }

        switch (requestParams.RequestType) {
            case GET_STRING:
                if (requestParams.strParams != null && builder instanceof GetBuilder) {
                    for (String key : requestParams.strParams.keySet()) {
                        ((GetBuilder) builder).addParams(key, requestParams.strParams.get(key));
                    }
                }
                break;
            case POST_STRING:

                break;
            case POST_FORM:
                if (requestParams.strParams != null && builder instanceof PostFormBuilder) {
                    for (String key : requestParams.strParams.keySet()) {
                        ((PostFormBuilder) builder).addParams(key, requestParams.strParams.get(key));
                    }
                }
                if (requestParams.submitFileList != null && builder instanceof PostFormBuilder) {
                    for (HttpRequestParams.FileInput file : requestParams.submitFileList) {
                        ((PostFormBuilder) builder).addFile(file.key, file.filename, file.file);
                    }
                }
                break;
        }
        return builder;
    }


    private void setRequestCallback(OkHttpRequestBuilder builder, final IHttpResponseCallBack<String> responseCallBack, HttpRequestParams requestParams) {
        try {
            RequestCall call = builder.build();
            call.connTimeOut(requestParams.connectTimeout);
            call.readTimeOut(requestParams.getTimeout);

            call.execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    responseCallBack.onFail(Constant.RESPOND_ERROR_NET, e.getMessage(), id + "");
                    responseCallBack.onFinished();
                }

                @Override
                public void onResponse(String response, int id) {
                    Log.i(TAG, "Success  ");
                    responseCallBack.onSuccess(response);
                    responseCallBack.onFinished();
                }
            });
        } catch (Exception e) {
            responseCallBack.onFail(Constant.RESPOND_ERROR_NET, e.getMessage(),"");
        }


    }
}
