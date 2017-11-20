package com.raqust.bluko.module.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

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
        return  R.layout.activity_video;
    }

    @OnClick({R.id.local_exoplayer,R.id.local_ijkplayer,R.id.local_videoview
    ,R.id.net_exoplayer,R.id.net_ijkplayer,R.id.net_videoview})
    public void onclic(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.local_exoplayer:
                break;
            case R.id.local_ijkplayer:
                intent = new Intent(VideoActivity.this, ijkActivity.class);
                intent.putExtra("url","content://media/external/video/media/2151746");
                startActivity(intent);
                break;
            case R.id.local_videoview:
                 intent = new Intent(VideoActivity.this, VideoViewActivity.class);
                intent.putExtra("url","content://media/external/video/media/2151746");
                startActivity(intent);
                break;
            case R.id.net_exoplayer:
                break;
            case R.id.net_ijkplayer:
                intent = new Intent(VideoActivity.this, ijkActivity.class);
                intent.putExtra("url","http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
                startActivity(intent);
                break;
            case R.id.net_videoview:
                intent = new Intent(VideoActivity.this, VideoViewActivity.class);
                intent.putExtra("url","http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
                startActivity(intent);
                break;

        }
    }
}
