package com.raqust.bluko.module.video;

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
public class VideoViewActivity extends BaseActivity {

    @BindView(R.id.video)
    public VideoView mVideoView;

    private MediaController mMediaController;

    @Override
    public void initViews() {
        mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);

        loadView(getIntent().getStringExtra("url"));

    }
    public void loadView(String path) {
        Uri uri = Uri.parse(path);
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //         mp.setLooping(true);
                mp.start();// 播放
                Toast.makeText(VideoViewActivity.this, "开始播放！", Toast.LENGTH_LONG).show();
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(VideoViewActivity.this, "播放完毕", Toast.LENGTH_SHORT).show();
            }
        });

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

}
