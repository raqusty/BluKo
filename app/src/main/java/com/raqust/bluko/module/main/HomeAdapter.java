package com.raqust.bluko.module.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raqust.bluko.R;
import com.raqust.bluko.common.AbstractFooterAdapter;

import java.util.List;

/**
 * Created by zehao on 2017/9/25.
 */

public class HomeAdapter extends AbstractFooterAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public HomeAdapter(Context context, List list) {
        super(context, list, false);
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RylViewHolder onCreateValidViewHolder(ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindValidViewHolder(RylViewHolder holder, int position) {
        ((ViewHolder) holder).mText.setText("作品 " + mDataList.get(position));
    }

    @Override
    protected int getValidViewType(int position) {
        return 1;
    }

    class ViewHolder extends RylViewHolder {
        ImageView mImage;

        TextView mText;

        ViewHolder(View view) {
            super(view);
            mImage = (ImageView) view.findViewById(R.id.layout_item_image);
            mText = (TextView) view.findViewById(R.id.layout_item_text);

        }
    }


}
