package com.raqust.bluko.common.net.Params;

/**
 * Created by zj on 2016/7/11.
 */
public enum HttpRequestType {

    //POST是默认按表单键值对上传的--暂时常用的就这些，貌似一般的put方式不支持表单解析，所以这个要注意了(POST_MULTIPART_EXTRA:是不加密的  不拼成data  直接字符串键值对的)
    GET, POST_CONTENT, POST_FORM, POST_JSON, POST_MULTIPART,POST_MULTIPART_EXTRA, DELETE, PUT_CONTENT, PUT_FORM, PUT_JSON, PUT_MULTIPART



    /*这里做个详细的说明*/
    //GET:参数直接往requestParams.strParams即可，如果设置了加密模式的话，也就是requestParams.isAESKey为true的话，会在NetUtils进行一定的处理，先把requestParams.strParams转为json字符串，
    //然后加密形成一个data:json加密字符串的形式传上去
    //POST_CONTENT：直接设置requestParams.bodyContent即可，在加密模式下，会在NetUtils进行加密处理。
    //POST_FORM,POST_JSON：直接设置requestParams.strParams即可，没做任何加密处理暂时
    //POST_MULTIPART:requestParams.submitFileList设置要上传的文件集合，requestParams.strParams设置要传的键值对，这里假如设置了加密模式，键值对的处理会跟GET方式一样
    //POST_MULTIPART_EXTRA:跟POST_MULTIPART几乎一样，区别在于，键值对没有额外处理，也不加密
    //剩下其他方式基本在这里都没用到，也没做什么加密处理的
}
