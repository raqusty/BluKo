package com.raqust.bluko.module.ChooseView;

import android.content.pm.ActivityInfo;
import android.support.v7.widget.Toolbar;

import com.example.matisse.Matisse;
import com.example.matisse.MimeType;
import com.example.matisse.engine.impl.GlideEngine;
import com.example.matisse.internal.entity.CaptureStrategy;
import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;

/**
 * Created by zehao on 2017/9/25.
 */

public class ChooseActivity extends BaseActivity {


    @Override
    public void initViews() {
//        Matisse.from(ChooseActivity.this)
//                .choose(MimeType.allOf())
//                .countable(true)
//                .maxSelectable(9)
////                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                .thumbnailScale(0.85f)
//                .imageEngine(new GlideEngine())
//                .forResult(1111);

        Matisse.from(ChooseActivity.this)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.raqust.bluko.fileprovider"))
                .maxSelectable(9)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(1111);


//        Matisse.from(ChooseActivity.this)
//                .choose(MimeType.ofVideo())
//                .showSingleMediaType(true)
//                .maxSelectable(3)
//                .forResult(2222);
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
        return R.layout.activity_choose;
    }

    @Override
    public Toolbar setToolBar() {
        return null;
    }


}
