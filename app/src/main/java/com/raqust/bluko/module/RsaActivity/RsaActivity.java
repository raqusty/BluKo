package com.raqust.bluko.module.RsaActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.raqust.bluko.common.utils.RSAEncryptUtil;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    public void initViews() {
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
                break;
            case R.id.text3:
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
