package com.raqust.bluko.common;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.raqust.bluko.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 1.在实例化对象时决定需不需要用footer
 * 2.footer的样式是固定的，当可以改变文本
 * 3.数据类型是泛型，可以随意使用数据类型
 * 4.刷新数据用插入的方式，减少数据刷新数据量，可以一定程度避免图片闪烁问题（建议使用这个刷新）
 *
 * @param <T>
 */
public abstract class AbstractFooterAdapter<T> extends RecyclerView.Adapter<AbstractFooterAdapter.RylViewHolder> {

    public static final int TYPE_TIP_FOOTER = 0;

    public static final int TYPE_TIP_EMTPY_FOOTER = 1;
    public static final int TYPE_TIP_LOADING_FOOTER = 2;

    protected List<T> mDataList = new ArrayList<T>();

    protected Context mContext;

    //是否要footer
    private boolean mHasFooter = false;
    //Footer的显示样式
    protected int mFooterState = TYPE_TIP_EMTPY_FOOTER;

    private View mFooterView;
    private ProgressBar mProgressBar;
    private TextView mTip;
    private String mTipString;

    public AbstractFooterAdapter(Context context, List<T> list) {
        this(context, list, false);
    }

    public AbstractFooterAdapter(Context context, List<T> list, boolean isFooter) {
        mContext = context;
        mDataList = list;
        mHasFooter = isFooter;
        if (mHasFooter) {
            mFooterView = LayoutInflater.from(mContext).inflate(R.layout.layout_load_more_footer_view, null);
            mProgressBar = (ProgressBar) mFooterView.findViewById(R.id.progress_bar_footer);
            mTip = (TextView) mFooterView.findViewById(R.id.tv_tips_footer);
            mTipString = mContext.getString(R.string.no_more_data);
        }

        //让图片不闪烁
        setHasStableIds(true);
    }

    /**
     * 刷新数据的方法（仅仅刷新刚刚添加进来的数据）
     * 建议用这个，因为他是刷新新加入的那部分数据，这样能减少所有数据一起重汇的消耗
     * 区别就是 notifyDataSetChanged  和 notifyItemRangeInserted的区别
     *
     * @param position 从哪个数据开始刷新
     */
    public void notifyDataChange(int position) {
        notifyDataChange(position, TYPE_TIP_LOADING_FOOTER);
    }

    public void notifyDataChange(int position, int footerState) {
        mFooterState = footerState;
        if (position == 0) {
            notifyDataSetChanged();
        } else if (position != mDataList.size()) {
            notifyItemRangeInserted(position, mDataList.size() - position);
        }
    }

    /**
     * 根据条件判断是否需要显示footer
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mFooterView != null && mHasFooter &&
                mDataList.size() > 0 ? mDataList.size() + 1 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //先判断是否最后一个，如果是，再判断是不是需要显示footer
        if (mDataList.size() == position && mFooterView != null && mHasFooter) {
            return TYPE_TIP_FOOTER;
        } else {
            return getValidViewType(position);
        }
    }

    @Override
    public void onBindViewHolder(AbstractFooterAdapter.RylViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TIP_FOOTER) {
            if (mHasFooter) {
                switch (mFooterState) {
                    case TYPE_TIP_EMTPY_FOOTER:
                        mTip.setText(mTipString);
                        mProgressBar.setVisibility(View.GONE);
                        break;
                    case TYPE_TIP_LOADING_FOOTER:
                        mTip.setText(mContext.getString(R.string.data_loading));
                        mProgressBar.setVisibility(View.VISIBLE);
                        break;
                    default:
                        mTip.setText(mContext.getString(R.string.data_loading));
                        mProgressBar.setVisibility(View.VISIBLE);
                        break;
                }
            }
        } else {
            onBindValidViewHolder(holder, position);
        }
    }

    @Override
    public RylViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TIP_FOOTER) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            parent.addView(mFooterView, layoutParams);
            return new FooterHolder(mFooterView, viewType);
        } else {
            return onCreateValidViewHolder(parent);
        }
    }

    /**
     * 卡片创建
     */
    public abstract RylViewHolder onCreateValidViewHolder(ViewGroup parent);

    /**
     */
    public abstract void onBindValidViewHolder(RylViewHolder holder, int position);

    /**
     * 子类在此方法中返回对应的viewType
     */
    protected abstract int getValidViewType(int position);

    protected class RylViewHolder extends RecyclerView.ViewHolder {

        public RylViewHolder(View itemView) {
            super(itemView);

        }

    }

    protected class FooterHolder extends RylViewHolder {

        public FooterHolder(View view, int type) {
            super(view);
        }
    }

    /************* 以下 让图片显示不 闪烁  ************/
    @Override
    public long getItemId(int position) {
        return position;
    }
    /************* 以上 让图片显示不 闪烁  ************/

    /************* 以下 如果是gridLayoutManager，处理一下，让foot占一整行，************/
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == mDataList.size())
                        return gridManager.getSpanCount();
                    return GridLayoutIsSpan(position, gridManager);
                }
            });
        }
    }

    /**
     * 子类需要用 onAttachedToRecyclerView 的话，用这个
     */
    protected int GridLayoutIsSpan(int position, GridLayoutManager gridManager) {
        return 1;
    }

    @Override
    public void onViewAttachedToWindow(AbstractFooterAdapter.RylViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /************* 以上 如果是gridLayoutManager，处理一下，让foot占一整行，************/

    /************* 以下 adapter 点击事件************/
    protected OnItemClickLitener mAdapterClick;

    public void setOnItemClickLitener(OnItemClickLitener onClick) {
        mAdapterClick = onClick;
    }

    public interface OnItemClickLitener {
        public void onClick(int point);
    }
    /************* 以上 adapter 点击事件************/
}