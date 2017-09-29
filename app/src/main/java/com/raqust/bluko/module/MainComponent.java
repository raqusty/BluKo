package com.raqust.bluko.module;

import com.raqust.bluko.module.main.FirstActivity;

import dagger.Component;


/**
 * Created on 2017/9/29.
 * Introduce :
 * Author : zehao
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(FirstActivity activity);

}
