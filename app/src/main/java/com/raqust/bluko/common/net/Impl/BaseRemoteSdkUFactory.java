package com.raqust.bluko.common.net.Impl;

/**
 * Created on 2017/9/27.
 * Introduce :
 * Author : zehao
 */

public class BaseRemoteSdkUFactory {

    public IBaseRemoteSdk createIBaseRemoteSdk(){
        return new BaseRemoteOkImpl();
    }
}
