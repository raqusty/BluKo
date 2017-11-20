package com.raqust.bluko.module.video;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by linzehao
 * time: 2017/11/20.
 * info:
 */
public class VideoActivity extends BaseActivity {


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
        return  R.layout.activity_video_view;
    }

    @OnClick({R.id.local_exoplayer,R.id.local_ijkplayer,R.id.local_videoview
    ,R.id.net_exoplayer,R.id.net_ijkplayer,R.id.net_videoview})
    public void onclic(View view){
        switch (view.getId()){
            case R.id.local_exoplayer:
                Log.i("linzehao","local_exoplayer");
                break;
            case R.id.local_ijkplayer:
                Log.i("linzehao","local_ijkplayer");
                break;
            case R.id.local_videoview:
                Log.i("linzehao","local_videoview");
                break;
            case R.id.net_exoplayer:
                Log.i("linzehao","net_exoplayer");
                break;
            case R.id.net_ijkplayer:
                Log.i("linzehao","net_ijkplayer");
                break;
            case R.id.net_videoview:
                Log.i("linzehao","net_videoview");
                break;

        }
    }
}
