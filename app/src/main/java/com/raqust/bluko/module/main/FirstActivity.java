package com.raqust.bluko.module.main;

import android.graphics.Rect;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zehao on 2017/9/25.
 */

public class FirstActivity extends BaseActivity {
    @BindView(R.id.commond_recycler)
    CommonRecyclerView mRecyclerView;

    HomeAdapter mAdapter;

    private List<ImageEntity> mListData = new ArrayList<>();
    private List<String> mImageList = new ArrayList<>();

    @Override
    public void initViews() {

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new HomeAdapter(mContext, mListData);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(16));
        mAdapter.setOnItemClickLitener(new AbstractFooterAdapter.OnItemClickLitener() {
            @Override
            public void onClick(int point) {


            }
        });

        mRecyclerView.setScrollListten(new CommonRecyclerView.ScrollListten() {
            @Override
            public void onScrolledToBottom() {
                Log.i("linzehao", "onScrolledToBottom");

            }
        });

        mImageList.add("http://res11.lamian.tv/lamian//822/20170822/15033872244123500_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//821/20170821/15033177114051520_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//821/20170821/15033165172151790_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//821/20170821/15033025373086470_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//818/20170818/15030379104065540_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//816/20170816/15028871859368510_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//815/20170815/15027678741853030_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//630/20170630/14988054629564710_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//629/20170629/14987258696271810_262_147_0.jpg");
        mImageList.add("http://res11.lamian.tv/lamian//628/20170628/14986171803083730_262_147_0.jpg");

        ImageEntity imageEntity;
        for (int i = 0; i < 10; i++) {
            imageEntity = new ImageEntity(i + " " + i, mImageList.get(i));
            mListData.add(imageEntity);
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
        return R.layout.commond_recyclerview_swipe;
    }

    @Override
    public Toolbar setToolBar() {
        return mToolbar;
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
