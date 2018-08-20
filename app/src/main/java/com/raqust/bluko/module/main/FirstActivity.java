package com.raqust.bluko.module.main;

import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.raqust.bluko.R;
import com.raqust.bluko.common.AbstractFooterAdapter;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;
import com.raqust.bluko.common.widget.CommonRecyclerView;
import com.raqust.bluko.module.main.entity.EventEntity;
import com.raqust.bluko.module.main.entity.EventListEntity;
import com.raqust.bluko.module.main.presenter.HomePresenter;
import com.raqust.bluko.module.main.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by zehao on 2017/9/25.
 */

public class FirstActivity extends BaseActivity implements IHomeView {
    @BindView(R.id.commond_recycler)
    CommonRecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    private HomeAdapter mAdapter;
    private List<EventEntity> mListData = new ArrayList<>();

    private int mCurPage = 1;

    @Inject
    HomePresenter mPresenter;

    @Override
    public void initViews() {

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new HomeAdapter(mContext, mListData);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(16));
        mCurPage = 1;
        requestData(mCurPage);
        Log.i("linzehao", getIntent().getStringExtra("articleId"))
        ;

    }

    private void requestData(int page) {
        mPresenter.getCommentList(1 + "", page);
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClickLitener(new AbstractFooterAdapter.OnItemClickLitener() {
            @Override
            public void onClick(int point) {
            }
        });

        mRecyclerView.setScrollListten(new CommonRecyclerView.ScrollListten() {
            @Override
            public void onScrolledToBottom() {
                mCurPage++;
                requestData(mCurPage);

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurPage = 1;
                requestData(mCurPage);
            }
        });

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
        return R.layout.commond_recyclerview_swipe;
    }

    @Override
    public Toolbar setToolBar() {
        return mToolBar;
    }

    @Override
    public void setCommentList(EventListEntity result) {
        if (mCurPage == 1)
            mListData.clear();
        int postion = mListData.size();
        mListData.addAll(result.getList());
        mAdapter.notifyDataChange(postion);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            int index = parent.getChildAdapterPosition(view);
            if (index % 2 == 1) {
                outRect.right = space;
                outRect.left = space / 2;
            } else {
                outRect.right = space / 2;
                outRect.left = space;
            }
            outRect.bottom = space;
        }
    }
}
