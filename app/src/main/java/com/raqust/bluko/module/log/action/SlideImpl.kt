package com.raqust.bluko.module.log.action

import com.raqust.bluko.module.log.SaveManager

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:滑动事件实现
 */
class SlideImpl : InterfaceAction {

    override fun logAction(string: String) {
        SaveManager.saveSlideAction(string)
    }

}