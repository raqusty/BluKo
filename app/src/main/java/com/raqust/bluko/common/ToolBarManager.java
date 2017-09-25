package com.raqust.bluko.common;

import android.support.v7.widget.Toolbar;

import com.raqust.bluko.R;


public class ToolBarManager {

    // 导航栏实例
    private final Toolbar mToolbar;

    /**
     * 构造方法
     *
     * @param toolbar 导航栏实例
     */
    public ToolBarManager(Toolbar toolbar) {
        this.mToolbar = toolbar;

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        if (mToolbar != null) {
            //设置左按钮，统一
            mToolbar.setNavigationIcon(R.mipmap.nav_back_btn);
            //设置主标题，因为这个主标题，规定居左，所以不用
            mToolbar.setTitle("");
        }
    }
}
