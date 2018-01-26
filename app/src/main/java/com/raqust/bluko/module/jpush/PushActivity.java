package com.raqust.bluko.module.jpush;

import android.view.View;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by linzehao
 * time: 2018/1/24.
 * info:
 */

public class PushActivity extends BaseActivity {

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

    String TAG = "linzehao";

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

    private Boolean isKey = false;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.text1:
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
