package com.raqust.bluko.module;

import com.raqust.bluko.module.main.view.IHomeView;

import dagger.Module;
import dagger.Provides;


/**
 * Created on 2017/9/29.
 * Introduce :
 * Author : zehao
 */

@Module
public class MainModule {
    private final IHomeView mView;

    public MainModule(IHomeView view) {
        mView = view;
    }

    @Provides
    IHomeView provideMainView() {
        return mView;
    }
}