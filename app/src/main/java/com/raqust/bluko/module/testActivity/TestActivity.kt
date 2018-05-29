package com.raqust.bluko.module.testActivity

import android.content.Intent
import android.util.Log
import com.raqust.bluko.R
import com.raqust.bluko.common.activity.BaseActivity
import com.raqust.bluko.common.activity.ToolBarManager
import com.raqust.bluko.common.message.MsgConstants
import com.raqust.bluko.common.utils.NotificationsUtils
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
//            NotificationsUtils.handleNotification(this, count.toString(), "adsfasdf", "3333", intent)

           val entity=  ChatDBManager.getChatByChatId("222-5555")
            entity?.time = System.currentTimeMillis()
            entity?.lastMsg="ssfsdfadfa"
            ChatDBManager.updataOntChatInfo(entity)
        }
        two.setOnClickListener {
            //            timer(period = 1 * 4 * 1000, action = {
//               val curTime = System.currentTimeMillis()
//               Log.i("linzehao","timer "+((curTime -lastTime ) ))
//               lastTime = curTime
//            })

            val list = mutableListOf<ChatEntity>()
            list.add(ChatEntity("sss",System.currentTimeMillis(),"111","111","111","111"
                    , UserInfo(11,"111","111",1), UserInfo(222,"222","222",1)))
            list.add(ChatEntity("33",System.currentTimeMillis(),"111","111","111","111"
                    , UserInfo(5555,"111","111",1), UserInfo(222,"222","222",1)))
            list.add(ChatEntity("123",System.currentTimeMillis(),"111","111","111","111"
                    , UserInfo(222,"111","111",1), UserInfo(222,"222","222",1)))
            list.add(ChatEntity("gasd",System.currentTimeMillis(),"111","111","111","111"
                    , UserInfo(333,"111","111",1), UserInfo(222,"222","222",1)))

//            ChatDBManager.insertChatData(ChatList(list))ChatList(list)
            ChatDBManager.queryChatData()?.forEach {
                Log.i("linzehao","111   "+it._id + "  "+ it.chaterId+"   "+ it.lastMsg +"  time "+ it.time)
            }
        }
    }

    override fun initToolBar(navigationBarMgr: ToolBarManager?) {


    }

    override fun getContentViewResId() = R.layout.activity_two_text
}