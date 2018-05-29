package com.raqust.bluko.common.extend

/**
 * Created by linzehao
 * time: 2018/5/29.
 * info:
 */
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import java.util.NoSuchElementException

fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun SQLiteDatabase.clear(tableName: String){
    execSQL("delete from $tableName")
}

fun SelectQueryBuilder.byId(id: Long) = whereSimple("_id = ?", id.toString())


fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V?>> =
        map({ Pair(it.key, it.value) }).toTypedArray()

inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    this.mapNotNull { predicate(it) }
            .forEach { return it }
    throw NoSuchElementException("No element matching predicate was found.")
}