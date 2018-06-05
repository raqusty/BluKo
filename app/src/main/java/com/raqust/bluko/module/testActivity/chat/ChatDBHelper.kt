package com.raqust.bluko.module.testActivity.chat

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.raqust.bluko.MyApplication
import org.jetbrains.anko.db.*

/**
 * 数据库操作助手类
 * Created by linzehao on 18-5-28.
 */
internal class ChatDBHelper(ctx: Context = MyApplication.instance) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "chat.db"
        val DB_VERSION = 1
        val instance by lazy { ChatDBHelper() }

    }

    override fun onCreate(db: SQLiteDatabase) {
        //创建设备信息表
        db.createTable(ChatDbTable.TABLE_NAME, true,
                ChatDbTable.PRIMARY_KEY_ID to INTEGER + PRIMARY_KEY + UNIQUE,
                ChatDbTable.CHATERTYPE to TEXT + DEFAULT("''"),
                ChatDbTable.CHATERID to TEXT + DEFAULT("''"),
                ChatDbTable.CHATERICON to TEXT + DEFAULT("''"),
                ChatDbTable.CHATERNAME to TEXT + DEFAULT("''"),
                ChatDbTable.ATTACHMENT to TEXT + DEFAULT("''"),
                ChatDbTable.LETTERID to TEXT + DEFAULT("''"),
                ChatDbTable.CHATERBODY to TEXT + DEFAULT("''"),
                ChatDbTable.LASTMSG to TEXT + DEFAULT("''"),
                ChatDbTable.TIME to INTEGER + DEFAULT("0"),
                ChatDbTable.UNREADCOUNT to INTEGER + DEFAULT("0"),
                ChatDbTable.TAG1 to TEXT + DEFAULT("''"))

    }

    /**
     * 参照修改http://blog.csdn.net/my_truelove/article/details/70196028
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.dropTable(DeviceInfoTable.TABLE_NAME, true)
//        db.dropTable(BusinessInfoTable.TABLE_NAME, true)
//        db.dropTable(UploadCacheTable.TABLE_NAME, true)
//        onCreate(db)
    }
}