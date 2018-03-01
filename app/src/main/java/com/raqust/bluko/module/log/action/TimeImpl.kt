package com.raqust.bluko.module.log.action

import com.raqust.bluko.module.log.SaveManager

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:事件事件实现
 */
class TimeImpl : InterfaceAction {

    private var startName = ""

    override fun logAction(string: String) {

        SaveManager.saveTimeAction(string)
    }

}