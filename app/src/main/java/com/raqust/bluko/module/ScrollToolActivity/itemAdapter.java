package com.raqust.bluko.module.ScrollToolActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raqust.bluko.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzehao
 * time: 2017/12/21.
 * info:
 */

public class itemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<String> mlist = new ArrayList<String>();
    LayoutInflater mLayoutInflater ;
    Context mContext;

    itemAdapter( List<String> l ,Context context){
        mlist = l;
        mContext= context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_scroll_item,parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}