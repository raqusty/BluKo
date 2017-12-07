package com.raqust.bluko.module;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jiguang.share.android.api.AuthListener;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.android.model.AccessTokenInfo;
import cn.jiguang.share.android.model.BaseResponseInfo;
import cn.jiguang.share.android.model.UserInfo;
import cn.jiguang.share.android.utils.Logger;

/**
 * Created by linzehao
 * time: 2017/12/6.
 * info:
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.text1)
    TextView QQLogin;

    @BindView(R.id.text2)
    TextView WeiBoLogin;

    @BindView(R.id.text3)
    TextView WeiXinLogin;

    @BindView(R.id.text4)
    TextView QQShare;

    @BindView(R.id.text5)
    TextView WeiBoShare;

    @BindView(R.id.text6)
    TextView WeiXinShare;

    private ProgressDialog progressDialog;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String toastMsg = (String) msg.obj;
            Toast.makeText(LoginActivity.this,toastMsg,Toast.LENGTH_SHORT).show();
        }
    };

    private PlatActionListener mPlatActionListener = new PlatActionListener() {
        @Override
        public void onComplete(Platform platform, int action, HashMap<String, Object> data) {
            if(handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享成功";
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            if(handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享失败:" + (error != null ? error.getMessage() : "");
                handler.sendMessage(message);
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            if(handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享取消";
                handler.sendMessage(message);
            }
        }
    };

    @Override
    public void initViews() {
        JShareInterface.setDebugMode(true);
        JShareInterface.init(this);
        Log.i("linzehao","asdf");
        List<String> platforms = JShareInterface.getPlatformList();
        for(String str:platforms){
            Log.i("linzehao",str);
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }

    private String TAG = "linzehao";

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void Click(View v) {
        ShareParams shareParams = new ShareParams();
        switch (v.getId()) {
            case R.id.text1:
                if (!JShareInterface.isAuthorize("QQ")) {
                    JShareInterface.authorize("QQ", mAuthListener);
                } else {
                    JShareInterface.removeAuthorize("QQ", mAuthListener);
                }
                break;
            case R.id.text2:
                JShareInterface.getUserInfo("QQ", mAuthListener);
                break;
            case R.id.text3:
                break;
            case R.id.text4:
                //这里以分享链接为例
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                shareParams.setTitle("123");
                shareParams.setText("333");
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                shareParams.setUrl("http://docs.jiguang.cn/jshare/client/Android/android_api/");
                JShareInterface.share("QQ", shareParams, mPlatActionListener);
                break;
            case R.id.text5:
                break;
            case R.id.text6:
                break;
            default:
                break;
        }
    }


    AuthListener mAuthListener = new AuthListener() {
        @Override
        public void onComplete(Platform platform, int action, BaseResponseInfo data) {
            Logger.dd(TAG, "onComplete:" + platform + ",action:" + action + ",data:" + data);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    if (data instanceof AccessTokenInfo) {        //授权信息
                        String token = ((AccessTokenInfo) data).getToken();//token
                        long expiration = ((AccessTokenInfo) data).getExpiresIn();//token有效时间，时间戳
                        String refresh_token = ((AccessTokenInfo) data).getRefeshToken();//refresh_token
                        String openid = ((AccessTokenInfo) data).getOpenid();//openid
                        //授权原始数据，开发者可自行处理
                        String originData = data.getOriginData();
                        toastMsg = "授权成功:" + data.toString();
                        Logger.dd(TAG, "openid:" + openid + ",token:" + token + ",expiration:" + expiration + ",refresh_token:" + refresh_token);
                        Logger.dd(TAG, "originData:" + originData);
                    }
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权成功";
                    break;
                case Platform.ACTION_USER_INFO:
                    if (data instanceof UserInfo) {      //第三方个人信息
                        String openid = ((UserInfo) data).getOpenid();  //openid
                        String name = ((UserInfo) data).getName();  //昵称
                        String imageUrl = ((UserInfo) data).getImageUrl();  //头像url
                        int gender = ((UserInfo) data).getGender();//性别, 1表示男性；2表示女性
                        //个人信息原始数据，开发者可自行处理
                        String originData = data.getOriginData();
                        toastMsg = "获取个人信息成功:" + data.toString();
                        Logger.dd(TAG, "openid:" + openid + ",name:" + name + ",gender:" + gender + ",imageUrl:" + imageUrl);
                        Logger.dd(TAG, "originData:" + originData);
                    }
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg;
                msg.sendToTarget();
            }
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            Logger.dd(TAG, "onError:" + platform + ",action:" + action + ",error:" + error);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "授权失败";
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权失败";
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "获取个人信息失败";
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg + (error != null ? error.getMessage() : "");
                msg.sendToTarget();
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            Logger.dd(TAG, "onCancel:" + platform + ",action:" + action);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "取消授权";
                    break;
                // TODO: 2017/6/23 删除授权不存在取消
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "取消获取个人信息";
                    break;
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(1);
                msg.obj = toastMsg;
                msg.sendToTarget();
            }
        }
    };




}
