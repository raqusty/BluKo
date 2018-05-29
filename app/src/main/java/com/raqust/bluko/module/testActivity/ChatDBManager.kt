package com.raqust.bluko.module.testActivity

import android.util.Log
import com.raqust.bluko.common.extend.toVarargArray
import org.jetbrains.anko.db.*

/**
 * 日志数据库操作管理类
 * Created by linzehao on 18-3-1.
 */
object ChatDBManager {

    /**
     * 获取一条数据，通过chatID
     */
    fun getChatByChatId(chatId:String):ChatInfoEntity?{
        var entity :ChatInfoEntity ?=null
        try {
            entity =   ChatDBHelper.instance.use {
                val builder = select(ChatDbTable.TABLE_NAME)
                builder.whereSimple("${ChatDbTable.CHATERID} = ?", chatId)
                builder.parseOpt(object : MapRowParser<ChatInfoEntity> {
                    override fun parseRow(columns: Map<String, Any?>): ChatInfoEntity {
                        return ChatInfoEntity(HashMap(columns))
                    }
                })
            }
        } catch (e: Exception) {
            Log.e("class=>LogDBManager", Log.getStackTraceString(e))
        }

        return entity
    }

    /**
     * 更新数据 一条数据 如果没有就插入
     */
    fun updataOntChatInfo(entity: ChatInfoEntity?) {
        if (entity== null)
            return
        ChatDBHelper.instance.use {
            try {
                synchronized(this) {
                    //result  可以判断是否更新成功
                    val result = update(ChatDbTable.TABLE_NAME, *(entity.map.toVarargArray()))
                            .whereSimple("${ChatDbTable.CHATERID} = ?", entity.chaterId).exec()
                    if (result == 0) {
                        insert(ChatDbTable.TABLE_NAME, *(entity.map.toVarargArray()))
                    }

                }
            } catch (e: Exception) {
                Log.e("class=>LogDBManager", Log.getStackTraceString(e))
            } finally {
                close()
            }
        }
    }

    /**
     * 查询数据
     */
    fun queryChatData(): List<ChatInfoEntity>? {
        var list :List<ChatInfoEntity> ?=null
        try {
            list =   ChatDBHelper.instance.use {
                val builder = select(ChatDbTable.TABLE_NAME)
                builder.orderBy(ChatDbTable.TIME, SqlOrderDirection.DESC)
                builder.parseList(object : MapRowParser<ChatInfoEntity> {
                    override fun parseRow(columns: Map<String, Any?>): ChatInfoEntity {
                        return ChatInfoEntity(HashMap(columns))
                    }
                })
            }
        } catch (e: Exception) {
            Log.e("class=>LogDBManager", Log.getStackTraceString(e))
        }

        return list
    }

    /**
     * 获取数据
     * 1.保存数据到本地，分更新 和 插入
     * 2.获取数据
     * 3.把数据按时间倒叙
     */
    fun getChatData(entityList: ChatList): List<ChatInfoEntity>? {
        ChatDBHelper.instance.use {
            try {
                entityList.list.forEach {
                    synchronized(this) {
                        val entity = ChatInfoEntity(it)
                        //result  可以判断是否更新成功
                        val result = update(ChatDbTable.TABLE_NAME, *(entity.map.toVarargArray()))
                                .whereSimple("${ChatDbTable.CHATERID} = ?", entity.chaterId).exec()
                        if (result == 0){
                            insert(ChatDbTable.TABLE_NAME, *(ChatInfoEntity(it).map.toVarargArray()))
                        }

                    }
                }

            } catch (e: Exception) {
                Log.e("class=>LogDBManager", Log.getStackTraceString(e))
            } finally {
                close()
            }
        }
        return queryChatData()
    }
}