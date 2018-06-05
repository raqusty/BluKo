package com.raqust.bluko.module.testActivity.chat

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
    fun getChatByChatId(chatId:String): ChatInfoEntity?{
        var entity : ChatInfoEntity?=null
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
     * 检查是否是最数据
     * 如果数据库有  且 （latterId 相等 或 time 小于等于 数据库） 就是最新的
     */
    fun checkNewData(chat:ChatInfoEntity): ChatInfoEntity? {
        var list :ChatInfoEntity ?=null
        try {
            list =   ChatDBHelper.instance.use {
                val builder = select(ChatDbTable.TABLE_NAME)
                builder.whereArgs("(${ChatDbTable.CHATERID} = {chaterId}) and ((${ChatDbTable.LETTERID}  = {letterId} )" +
                        "or (${ChatDbTable.TIME}  >= {time}))",
                        "chaterId" to chat.chaterId, "letterId" to chat.letterId, "time" to chat.time)
                builder.parseOpt(object : MapRowParser<ChatInfoEntity> {
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
     * 3.把数据按时间倒叙（取全部） 部分的没有排序
     */
    fun getChatData(entityList: ChatList,isDiff:Boolean = true): List<ChatInfoEntity>? {
        val list :MutableList<ChatInfoEntity> ?= mutableListOf()
        ChatDBHelper.instance.use {
            try {
                entityList.list.forEach {
                    synchronized(this) {
                        val entity = ChatInfoEntity(it)
                        //result   可以判断是否更新成功
                        val dbEntity = getChatByChatId(entity.chaterId)
                        if (dbEntity!=null){
                            if (entity.letterId != dbEntity.letterId && entity.time > dbEntity.time){
                                update(ChatDbTable.TABLE_NAME, *(entity.map.toVarargArray()))
                                        .whereSimple("${ChatDbTable.CHATERID} = ?", entity.chaterId).exec()
                            }
                            addChatList(entity.chaterId,list)
                        }else{
                            insert(ChatDbTable.TABLE_NAME, *(entity.map.toVarargArray()))
                            addChatList(entity.chaterId,list)
                        }

                    }
                }
            } catch (e: Exception) {
                Log.e("class=>LogDBManager", Log.getStackTraceString(e))
            } finally {
                close()
            }
        }
        return if (isDiff)
            list
        else
            queryChatData()
    }


    private fun addChatList(chatId:String,list :MutableList<ChatInfoEntity>?){
        val result = getChatByChatId(chatId)
        if (result!=null){
            list?.add(result)
        }
    }
}