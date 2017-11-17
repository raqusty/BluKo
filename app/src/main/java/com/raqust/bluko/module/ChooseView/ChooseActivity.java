package com.raqust.bluko.module.ChooseView;

import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raqust.bluko.R;
import com.raqust.bluko.common.AbstractFooterAdapter;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.raqust.bluko.common.widget.CommonRecyclerView;
import com.raqust.bluko.module.DaggerMainComponent;
import com.raqust.bluko.module.MainModule;
import com.raqust.bluko.module.main.HomeAdapter;
import com.raqust.bluko.module.main.entity.EventEntity;
import com.raqust.bluko.module.main.entity.EventListEntity;
import com.raqust.bluko.module.main.presenter.HomePresenter;
import com.raqust.bluko.module.main.view.IHomeView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by zehao on 2017/9/25.
 */

public class ChooseActivity extends BaseActivity   {


    @Override
    public void initViews() {
        Matisse.from(ChooseActivity.this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(1111);
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
        return R.layout.activity_choose_view;
    }

    @Override
    public Toolbar setToolBar() {
        return null;
    }


}
