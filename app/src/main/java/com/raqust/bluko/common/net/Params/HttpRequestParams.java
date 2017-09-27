package com.raqust.bluko.common.net.Params;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 2017/9/27.
 * Introduce :
 * Author : zehao
 */

public class HttpRequestParams {

    //http的提交方式
    public HttpRequestType RequestType;
    // 主机地址
    public String hostUrl;


    // 传递到服务器的头参数
    public HashMap<String, String> headParams;
    // 传递到服务器的参数
    public HashMap<String, String> strParams;
    //直接以字符串的流的形式作为参数（POST_CONTENT,PUT_CONTENT）
    public String bodyContent;
    // 上传到服务器的文件
    public ArrayList<FileInput> submitFileList;


    //  连接到服务器的超时时间，单位：毫秒
    public int connectTimeout ;
    // 从服务器获取数据的超时时间，单位：毫秒
    public int getTimeout ;

    private HttpRequestParams(Builder builder) {
        this.RequestType = builder.RequestType;
        this.hostUrl = builder.hostUrl;
        if (null != builder.headParams) {
            this.headParams = builder.headParams;
        } else {
            this.headParams = new HashMap<>();
        }
        if (null != builder.strParams) {
            this.strParams = builder.strParams;
        } else {
            this.strParams = new HashMap<>();
        }
        if (null != builder.submitFileList) {
            this.submitFileList = builder.submitFileList;
        } else {
            this.submitFileList = new ArrayList<>();
        }
        if (null != builder.bodyContent) {
            this.bodyContent = builder.bodyContent;
        } else {
            this.bodyContent = "";
        }
        this.connectTimeout = builder.connectTimeout;
        this.getTimeout = builder.getTimeout;
    }

    public static class Builder {
        //http的提交方式
        private HttpRequestType RequestType = HttpRequestType.POST_FORM;
        // url地址
        private String hostUrl = "";
        // 传递到服务器的头参数
        private HashMap<String, String> headParams;
        // 传递到服务器的参数
        private HashMap<String, String> strParams;
        // 上传到服务器的文件
        private ArrayList<FileInput> submitFileList;
        private String bodyContent = "";
        //  连接到服务器的超时时间，单位：秒
        private int connectTimeout = 15000;
        // 从服务器获取数据的超时时间，单位：秒
        private int getTimeout = 15000;


        public Builder() {
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setSubmitFileList(ArrayList<FileInput> submitFileList) {
            this.submitFileList = submitFileList;
            return this;
        }

        public Builder setGetTimeout(int getTimeout) {
            this.getTimeout = getTimeout;
            return this;
        }


        public Builder setHeadParams(HashMap<String, String> headParams) {
            this.headParams = headParams;
            return this;
        }


        public Builder setStrParams(HashMap<String, String> strParams) {
            this.strParams = strParams;
            return this;
        }


        public Builder setRequestType(HttpRequestType requestType) {
            this.RequestType = requestType;
            return this;
        }

        public Builder setHostUrl(String hostUrl) {
            this.hostUrl = hostUrl;
            return this;
        }

        public Builder setBodyContent(String bodyContent) {
            this.bodyContent = bodyContent;
            return this;
        }

        public HttpRequestParams build() {
            return new HttpRequestParams(this);
        }


    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

    }

}
