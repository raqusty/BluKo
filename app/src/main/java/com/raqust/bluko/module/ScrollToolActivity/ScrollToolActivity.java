package com.raqust.bluko.module.ScrollToolActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.activity.BaseActivity;
import com.raqust.bluko.common.activity.ToolBarManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by linzehao
 * time: 2017/12/21.
 * info:
 */

public class ScrollToolActivity extends BaseActivity {

    List<String> list = new ArrayList<String>();

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.myName1)
    TextView mTextView;

    @BindView(R.id.layout_item_head)
    TextView mItemHead;

    itemAdapter adapter;

    LinearLayoutManager manager;

    @Override
    public void initViews() {
        for (int i = 0; i < 40; i++) {
            list.add("" + i);
        }
        adapter = new itemAdapter(list, this);
        manager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayout viewGroug = (LinearLayout) manager.findViewByPosition(0);
                if (viewGroug != null){
                    View view = viewGroug.getChildAt(1);
                    if (dy > 0) {////向下滚动
                        if (viewGroug.getY() + view.getY() <= 0) {
                            mTextView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (viewGroug.getY() + view.getY() >= 0) {
                            mTextView.setVisibility(View.INVISIBLE);
                        }

                    }
                }

                LinearLayout viewGroug1 = (LinearLayout) manager.findViewByPosition(1);
                if(viewGroug1!=null){
                    View headView = viewGroug1.getChildAt(0);
                    if (dy > 0) {////向下滚动
                        if (viewGroug1.getY() + headView.getY() <= 0) {
                            mItemHead.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (viewGroug1.getY() + headView.getY() <= 0) {
                            mItemHead.setVisibility(View.INVISIBLE);
                        }
                    }
                }
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
        return R.layout.activity_scroll_tool;
    }

}
