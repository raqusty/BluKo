package com.raqust.bluko.module.testActivity

import android.content.Intent
import android.util.Log
import com.raqust.bluko.MyApplication
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.message.MsgConstants
import com.raqust.bluko.common.utils.NotificationsUtils
import com.raqust.bluko.module.testActivity.chat.ChatDBManager
import com.raqust.bluko.module.testActivity.chat.ChatEntity
import com.raqust.bluko.module.testActivity.chat.ChatList
import com.raqust.bluko.module.testActivity.chat.UserInfo
import kotlinx.android.synthetic.main.activity_two_text.*

/**
 * Created by linzehao
 * time: 2018/5/28.
 * info:
 */
class TestActivity : BaseActivity() {
    override fun initViews() {
    }

    override fun getToolBarResId() = 0

    var count = 0
    var lastTime = 0L

    override fun setListener() {
        one.setOnClickListener {
            count++
//            var intent: Intent? = null
//            intent = Intent(MsgConstants.INTENT_WEBVIEW)
//            NotificationsUtils.handleNotification(MyApplication.instance, count.toString(), "adsfasdf", "3333", intent)

           val entity=  ChatDBManager.getChatByChatId("222-5555")
//            entity?.time = System.currentTimeMillis()
            entity?.lastMsg="ssfsdfadfa"
//            entity?.letterId="3333"
//            ChatDBManager.updataOntChatInfo(entity)

            val list = mutableListOf<ChatEntity>()
            list.add(ChatEntity("123", System.currentTimeMillis(), "111", "323", "111", "111",11
                    , UserInfo(222, "111", "111", 1), UserInfo(222, "222", "222", 1)))
            list.add(ChatEntity("gasd", System.currentTimeMillis(), "111", "111", "111", "111",11
                    , UserInfo(333, "111", "111", 1), UserInfo(222, "222", "222", 1)))

            list.add(ChatEntity("gasd", System.currentTimeMillis(), "111", "111", "111", "111",11
                    , UserInfo(6666, "111", "111", 1), UserInfo(222, "222", "222", 1)))


            ChatDBManager.getChatData(ChatList(list))?.forEach {
                Log.i("linzehao","111   "+it._id + "  "+ it.chaterId+"   "+ it.lastMsg +"  time "+ it.time+ "  "+ it.letterId)
            }
        }
        two.setOnClickListener {
            //            timer(period = 1 * 4 * 1000, action = {
//               val curTime = System.currentTimeMillis()
//               Log.i("linzehao","timer "+((curTime -lastTime ) ))
//               lastTime = curTime
//            })
            count++
            val list = mutableListOf<ChatEntity>()
            list.add(ChatEntity("sss", System.currentTimeMillis(), "111", "111"+count, "111", "111",11
                    , UserInfo(11, "111", "111", 1), UserInfo(222, "222", "222", 1)))
            list.add(ChatEntity("33", System.currentTimeMillis(), "111", "111", "111", "111",11
                    , UserInfo(5555, "111", "111", 1), UserInfo(222, "222", "222", 1)))
            list.add(ChatEntity("123", System.currentTimeMillis(), "111", "111", "111", "111",11
                    , UserInfo(222, "111", "111", 1), UserInfo(222, "222", "222", 1)))
            list.add(ChatEntity("gasd", System.currentTimeMillis(), "111", "111", "111", "111",11
                    , UserInfo(333, "111", "111", 1), UserInfo(222, "222", "222", 1)))

            ChatDBManager.getChatData(ChatList(list),false)?.forEach {
                Log.i("linzehao","111   "+it._id + "  "+ it.chaterId+"   "+ it.lastMsg +"  time "+ it.time+ "  "+ it.letterId)
            }
        }
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {


    }

    override fun getContentViewResId() = R.layout.activity_two_text
}