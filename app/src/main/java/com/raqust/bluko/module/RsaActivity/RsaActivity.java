package com.raqust.bluko.module.RsaActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.raqust.bluko.common.utils.RSAEncryptUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import com.raqust.bluko.common.service.SignInterceptor;

import static com.raqust.bluko.common.utils.RSAEncryptUtil.publicKeyString;

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
                .addInterceptor(new SignInterceptor())//Application拦截器
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

    private Boolean isKey = false;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.text1:
                String encodeBase64 = RSAEncryptUtil.encryptByPublicKey(publicKeyString, "123");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao", RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr, encodeBase64));

                encodeBase64 = RSAEncryptUtil.encryptByPublicKey(publicKeyString, "qwerqwerq");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao", RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr, encodeBase64));

                encodeBase64 = RSAEncryptUtil.encryptByPublicKey(publicKeyString, "拎着哦ad放假1233 手动阀地方fff ,,,");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao", RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr, encodeBase64));

                encodeBase64 = RSAEncryptUtil.encryptByPublicKey(publicKeyString, "5674657!@$Q# asdf啊我额");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao", RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr, encodeBase64));

                encodeBase64 = RSAEncryptUtil.encryptByPublicKey(publicKeyString, "我哪里大师傅金坷垃时代峻峰奥打飞机阿道夫阿里的看法 阿道夫");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao", RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr, encodeBase64));

                encodeBase64 = RSAEncryptUtil.encryptByPublicKey(publicKeyString, "ffffffffffffffffffffffffffffffffffff");//Base64.encodeBase64String(encrypt);
                Log.i("linzehao", RSAEncryptUtil.decryptByPrivateKey(RSAEncryptUtil.privateKeyStr, encodeBase64));
                break;
            case R.id.text2:
                isKey = false;
//                Request request = new Request.Builder().url("http://app.dev.gc.xf.io/userCenterRsa/v1/publicKey").build();
                FormBody.Builder params=new FormBody.Builder();
                params.add("mobile", "15989147263");
                params.add("password", "1234567");
                params.add("type", "1");

                RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{\"type\":1,\"password\":\"1234567\",\"mobile\":\"15989147263\"}");
                Request request = new Request.Builder()
                        .url("http://app.dev.gc.xf.io//login/v2?encryptMap={\"password\":\"1234567\"}")
                        .post(body)
                        .build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "--" + response.networkResponse());
                    }
                });

                break;
            case R.id.text3:
                isKey = true;
                Request request1 = new Request.Builder().url("http://app.dev.gc.xf.io/userCenterRsa/v1/publicKey?encryptMap={\"password\":\"1234567\"}").get().build();
                okHttpClient.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "--" + response.networkResponse());
                    }
                });
                break;
            case R.id.text4:
                break;
            case R.id.text5:
                Gson gson2 = new GsonBuilder().enableComplexMapKeySerialization().create();
                Map<String, String> map = new HashMap<String, String>();
                map.put("key1", "value1");
                map.put("key2", "value2");
                map.put("key3", "value3");

                String jsonString = "asdf";
                Type type = new TypeToken<Map<String, String>>() {
                }.getType();
                Map<String, String> map2 = new Gson().fromJson(jsonString, type);

                String showString = "";
                for (String keyString : map2.keySet()) {
                    showString += keyString + ":" + map2.get(keyString) + "\n-----\n";
                }

                break;
            case R.id.text6:

                String str ="asdf";
                break;
            default:
                break;
        }
    }


}
