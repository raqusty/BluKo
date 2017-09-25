package com.raqust.bluko.module.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.raqust.bluko.R;
import com.raqust.bluko.common.AbstractFooterAdapter;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zehao on 2017/9/25.
 */

public class FirstActivity extends BaseActivity {
    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    HomeAdapter mAdapter;

    private List<String> mListData = new ArrayList<>();

    @Override
    public void initViews() {

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new HomeAdapter(mContext, mListData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new AbstractFooterAdapter.OnItemClickLitener() {
            @Override
            public void onClick(int point) {


            }
        });
        for (int i = 0; i < 10; i++) {
            mListData.add(i + " " + i);
        }
        mAdapter.notifyDataChange(0);

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
