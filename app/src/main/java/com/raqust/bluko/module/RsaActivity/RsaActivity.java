package com.raqust.bluko.module.RsaActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.raqust.bluko.common.utils.RSAEncryptUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by linzehao
 * time: 2018/1/24.
 * info:
 */

public class RsaActivity extends BaseActivity {

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

    OkHttpClient okHttpClient;

    String TAG = "linzehao";

    @Override
    public void initViews() {
        okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(appInterceptor)//Application拦截器
                .build();
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

    private Boolean isKey = false ;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }

    Interceptor appInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url();
            String s = url.url().toString();
            //---------请求之前-----
            Log.d(TAG,"app interceptor:begin");
            Response  response = chain.proceed(request);
            Log.d(TAG,"app interceptor:end");
            //---------请求之后------------
            if (!isKey){
                isKey = true;
                OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
                Request request2 = new Request.Builder().url("http://www.baidu.com")
                        .get().build();
                Call call = client.newCall(request2);
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

    };


    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.text1:
                String encodeBase64 = RSAEncryptUtil.encryptByPublicKey( RSAEncryptUtil.publicKeyString,"123");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao",RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr,encodeBase64));

                 encodeBase64 = RSAEncryptUtil.encryptByPublicKey( RSAEncryptUtil.publicKeyString,"qwerqwerq");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao",RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr,encodeBase64));

                 encodeBase64 = RSAEncryptUtil.encryptByPublicKey( RSAEncryptUtil.publicKeyString,"拎着哦ad放假1233 手动阀地方fff ,,,");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao",RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr,encodeBase64));

                 encodeBase64 = RSAEncryptUtil.encryptByPublicKey( RSAEncryptUtil.publicKeyString,"5674657!@$Q# asdf啊我额");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao",RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr,encodeBase64));

                 encodeBase64 = RSAEncryptUtil.encryptByPublicKey( RSAEncryptUtil.publicKeyString,"我哪里大师傅金坷垃时代峻峰奥打飞机阿道夫阿里的看法 阿道夫");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao",RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr,encodeBase64));

                 encodeBase64 = RSAEncryptUtil.encryptByPublicKey( RSAEncryptUtil.publicKeyString,"ffffffffffffffffffffffffffffffffffff");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao",RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr,encodeBase64));
                break;
            case R.id.text2:
                isKey = false;
                Request request = new Request.Builder().url("http://app.dev.gc.xf.io/userCenterRsa/v1/publicKey").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG,"--" + response.networkResponse());
                    }
                });

                break;
            case R.id.text3:
                isKey = true;
                Request request1 = new Request.Builder().url("http://app.dev.gc.xf.io/userCenterRsa/v1/publicKey").build();
                okHttpClient.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG,"--" + response.networkResponse());
                    }
                });
                break;
            case R.id.text4:
                break;
            case R.id.text5:
                break;
            case R.id.text6:
                break;
            default:
                break;
        }
    }


}
