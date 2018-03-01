package com.raqust.bluko.module.log.action

import com.raqust.bluko.module.log.SaveManager

/**
 * Created by linzehao
 * time: 2018/3/1.
 * info:点击事件实现
 */
class ClickImpl : InterfaceAction{

    override fun logAction(string: String) {
        SaveManager.saveClickAction(string)
    }

}