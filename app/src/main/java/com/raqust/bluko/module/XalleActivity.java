package com.raqust.bluko.module;

import android.text.TextUtils;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.xalle.admin.xalle.util.read.ChannelInfo;
import com.xalle.admin.xalle_android.AppReader;

import butterknife.BindView;

/**
 * Created by linzehao
 * time: 2017/12/5.
 * info:
 */

public class XalleActivity  extends BaseActivity {

    @BindView(R.id.text)
    TextView textView;

    @Override
    public void initViews() {
        ChannelInfo str = AppReader.getChannelInfo(this,"");
        if(str!=null)
            textView.setText(str.getChannel() + str.getExtraInfo());
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
        return R.layout.activity_xalle;
    }
}
