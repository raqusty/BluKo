package com.raqust.bluko.common.net.CallBack;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/9/27.
 * Introduce : json  list 数据回调
 * Author : zehao
 */
public abstract class JsonListResponseCallBack<T> extends IJsonBaseCallBack {

    private Class<T> classFile;

    public JsonListResponseCallBack(Class<T> classFile) {
        this.classFile = classFile;
    }


    /**
     * Http请求成功的回调方法
     *
     * @param code           后台返回码
     * @param msg            成功信息
     * @param responseResult 请求结果字符串，这个仅仅是成功之后的body部分，要不是String，要不就是File
     */
    public abstract void onSuccess(String code, String msg, String data, List<T> responseResult);



    public void preSuccess(String statusCode, String msg, String data) {
        ArrayList<T> mList = new ArrayList<T>();

        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(data).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, classFile));
        }
        onSuccess(statusCode, msg, data, mList);

    }
}
