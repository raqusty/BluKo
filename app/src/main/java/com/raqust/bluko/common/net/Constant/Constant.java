package com.raqust.bluko.common.net.Constant;

/**
 * Created on 2017/9/28.
 * Introduce : 网络的设置常数
 * 可能不同项目，不同业务会有不同的值，设置这里就好了
 * Author : zehao
 */

public class Constant {

    //链接超时时间
    public static final int CONNEC_TTIME_OUT = 1500;

    //响应超时时间
    public static final int RESPOND_TTIME_OUT = 1500;

    //错误代码 数据请求成功
    public static final int RESPOND_ERROR_SUCCESS = 0;

    //错误代码 数据解析异常
    public static final int RESPOND_ERROR_ANALYSIS = -101;
    public static final int RESPOND_ERROR_NET = -100;
    public static final String RESPOND_ERROR_ANALYSIS_MSG = "数据解析异常";

    //msg
    public static final String RESPOND_MSG = "msg";
    //code
    public static final String RESPOND_CODE = "code";
    //data
    public static final String RESPOND_DATA = "data";



}
