package com.raqust.bluko.module.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.raqust.bluko.R;
import com.raqust.bluko.common.AbstractFooterAdapter;

import java.util.List;

/**
 * Created by zehao on 2017/9/25.
 */

public class HomeAdapter extends AbstractFooterAdapter<ImageEntity> {
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
    public void onBindValidViewHolder(final RylViewHolder holder, int position) {
        ImageEntity imageEntity = mDataList.get(position);
        ((ViewHolder) holder).mText.setText("作品 " + imageEntity.getText());

        Glide.with(mContext).load(imageEntity.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade(1000)
                .placeholder(R.mipmap.no_data_image)
                .error(R.mipmap.no_net_image)
//                    .into(((MyViewHolder) holder).tv);
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        ((ViewHolder) holder).mImage.setImageDrawable(resource);
                    }
                });
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
