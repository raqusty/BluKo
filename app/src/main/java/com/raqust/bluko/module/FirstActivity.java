package com.raqust.bluko.module;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.BaseActivity;
import com.raqust.bluko.common.ToolBarManager;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/25.
 */

public class FirstActivity extends BaseActivity {
    @BindView(R.id.test)
    public TextView mText;

    @Override
    public void initViews() {
        mText.setText("asdfadf");
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
        return R.layout.activity_main;
    }

    @Override
    public Toolbar setToolBar() {
        return mToolbar;
    }
}
