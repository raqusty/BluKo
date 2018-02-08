package com.raqust.bluko.module.RsaActivity;

import android.text.TextUtils;
import android.util.Base64;
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
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
import com.raqust.bluko.common.utils.RsaEncrypt;

import static com.raqust.bluko.common.utils.RSAEncryptUtil.publicKeyString;
import static com.raqust.bluko.common.utils.RsaEncrypt.RSA;

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
                        .url("http://app.dev.gc.xf.io//login/v2?encryptMap={\"password\":\"123456\"}")
                        .post(body)
                        .build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.d(TAG, "123  " +  123);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String  body = response.body().string();
                        Log.d(TAG, "312" +  body);

                    }
                });

                break;
            case R.id.text3:
                isKey = true;
                Request request1 = new Request.Builder().url("http://app.dev.gc.xf.io/userCenterRsa/v1/publicKey?encryptMap={\"password\":\"123456\"}").get().build();
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

                String miwen = "u212mjFI9Ak6S9h02ZD39+cSLjxrx9tWo0PY369DFkbCoWfqk1wrlIJRRN5X1pvNlSRJn5emtgZm4znmrzdyp+YsJhAobA0av7J5bj/LW7qJHXT6OaSb6CwAL7IDI5Xa0AocQFW1v2sEnqtF/cgIWrASVYpaVyQ7cT022YzQ8N2t1RCtm+/pxb5pZFuY+LcAWY8YdOG0NNnWqri8ONfcn6cIo+fmPGKPjh77Rg3zTaGQ+w+mKlqCmrduEDJx2ZXKssVpetZybx9YjBVExujDN0+/vwdkp4ixefxM7rxGo1+lN9DsBgKuc+bmZ6A1g7oJGvRtO9ALiILjVVCjNvpBxQ==";
                String privateKeyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDZM43ZN1+r5U5OsTwODhIQJoH8nENRNGQadNaQHzEvP/72vAWXbrlz4qnD4rpuUSaIdddaBXUHO42IIiEMdgbwyvdr8D2ifHjFEO6O0PIErViywSsezJsM+kkYfn4hVzQu6jzZbHjdG4tDKJgu3eKdNsxpkFqT2+fDH9luxVPyn3+KAMUVXUe/Fbzc0l23V9h/niDjZ48U5XOwqWclRsgO/rA+ya0wj4PNwZA0BBK/tXnMZmCw2wTiAhajI8jQwv7yMIUOS50wsQemxwd91gAMo6Frc2EOEd7ioO8vZre9f2nS8uHDbL1aVL1gNh2xBTT4kCOrRCouzwUeKXcMFBPRAgMBAAECggEBAIwPf5gj6hC51I5QIe7aSfGaM1ZHGe3CgA7Dax0S0C+s7+gBhQWKxBIjht+nVBsRP2zduJn3TOj2ESVJBNC5R259I6G6ELS32VUSvxLfUEGN2tWiVQEOZamvt/UQlJtBVYgvSj/Wf/Xs7hfHlKMcOCazEIu+J3qTYtdjsV2J58FawkCLs4WTO1jFbtaXIwl33EndAweZImSttunfoPg+zDjZOybPbvugcA6NvMtmdBeQcVACzhYMhGdn/Lub1rHBTMKxbd/TJPzRAnUA/On/rftnGfacmOu13Hg+bDBX1wsKh63wd2o4I+5RCr/B2KLW82ORMR5D2GlYGOBoJJtVrUECgYEA+pfJtvHm/Nz+BK3nxl2pWjcNFicVDupJhBJvPeygUczaHxy+2w8xXCGiKpgngQaeUCj4wX1TyLj6YSs4EoZe7Rkm1JtWOS0okm3hdOJbNBH0hRwg+/bo3ks/vvxTry7svx9Zr96vU2ZHN2vMtBRXpUDNT06Zd8ET1dYwiPt502kCgYEA3eNR2Pmz2B2wSEf7m/f2DA0PnbOIHdEEQyq61NJTptxP2sAsFyNaJZIHHCKr/xe/0BkszhaDyRWpX4N8v0ngQ/TOAaMwYwCejem1jfxIUahhBRlQpBmGIXQBAlz01X+I1LnvDAqgdSnFkJATg3MWyuQtiTVtCFq+XRReHB+4eCkCgYBuJxq8GEl5DYt7cxZW1AUFav2Np1BSZho6u/+6MLGoQ2v+ERy0HlGMNhyarJdw1//vLJNsOjMCII2u+NiFDBveDRhMjJuyNm+HLlXUk0uT7/CUMnEtULO8Q0eaJECE2ROoT5eU/0YCWwsjCXfKEMcWFQ4qtlovkeeuL6DWFfTPQQKBgQCC6T0+8IPk/A8ndiDCspceIQ6Xkna6cLk9D5bPPZBRICaU/1CEDj1/cRp0xRgJFu/6TQAcTzhNiVQ2oBMXoPSJ44MvgCJqJtFlFQAi1zerxdYH9hmX199FGXYG+OUSmX4XU+PvjM2CkrSXSAnbQimuZtVe4ICFr1QlAoFLwoNJkQKBgQDcSb/tbR7bLzIgpgRaA68W/K5zSUI90695qAIMrHaXwGxQuEZvTrVBm9N0yHd3mQo24KJmxLGHGyr6yh/aYPiAA+A39ktHuyYHjXdE6FLAu3UE3pbna11/EeYF5FkLd0lm1YBzrJbVSnjejSlgKgAuS9SAnvIBPCnYTAFCxNBdsw==";
                try {
//                    byte[]  bytes = RsaEncrypt.decryptByPrivateKey(miwen.getBytes(),privateKeyStr.getBytes());
//                    Log.i("linzehao",new String(bytes,"UTF8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
                String miwen1 = "CucCQdZd4hgHVG32oMUHC9rAbmK6Ybo2TSPKWQmm/sNsX7ouIGPeekOqIejowow+aPvCeTS7WoGpkVQwut1/GweXcmVqtklWlLIxA5vwTfxlQsDA1PJW2sO36oiHzGnKkw+rG7Oiuh0GIdWUrAZYIXCBDxgjmI+HzgtSjYm516SbGcAzIGQTF1O7LQX+sUoScEyHJRe4Z1nLjC/Z9mh0gqubJVCNChQJl8xh3SXheIYjFTGDoQhVaPQUFRLeTvcay18OQSrszpry0GbNUu1JG0XgjeAJluTwzo97+MxxQM/O6KJxm7ejyCNnjG3O4SbxVs/zlUS5hRPQ8ql/aGtfvQ==";

                String a = "shang123";
//                try {
//                    byte []  puba = RsaEncrypt.encryptByPublicKey(Base64.decode(a,Base64.NO_WRAP), Base64.decode(RSAEncryptUtil.publicKeyString,Base64.NO_WRAP)  );
//                    Log.i("linzehao", "1232  "+ Base64.encodeToString(puba,Base64.NO_WRAP));
//                    Log.i("linzehao", "1222  "+ new String(puba,"UTF8"));
//                    puba = RsaEncrypt.decryptByPrivateKey(Base64.decode(miwen1,Base64.NO_WRAP),Base64.decode(RSAEncryptUtil.privateKeyStr,Base64.NO_WRAP));
////                    puba = RsaEncrypt.decryptByPrivateKey(puba,Base64.decode(RSAEncryptUtil.privateKeyStr,Base64.NO_WRAP));
//                    Log.i("linzehao", "2222  "+new String(puba,"UTF8"));
//                    Log.i("linzehao", "2223  "+ Base64.encodeToString(puba,Base64.NO_WRAP));
//
//
//                    byte []  puba1 = RsaEncrypt.encryptByPublicKey(a.getBytes("UTF-8"), Base64.decode(RSAEncryptUtil.publicKeyString,Base64.NO_WRAP)  );
//                    Log.i("linzehao", "3232  "+ Base64.encodeToString(puba1,Base64.NO_WRAP));
//                    puba1 = RsaEncrypt.decryptByPrivateKey(puba1,Base64.decode(RSAEncryptUtil.privateKeyStr,Base64.NO_WRAP));
//                    Log.i("linzehao", "3222  "+new String(puba1,"UTF8"));



//                } catch (Exception e) {
//                    e.printStackTrace();
//                }



                break;
            default:
                break;
        }
    }

    public  KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(2048, new SecureRandom());
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
