package com.example.dictionary.data

import android.content.Context
import com.example.dictionary.models.Word
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

const val DATABASE_ANH_VIET = "anh_viet.db"
const val DATABASE_VIET_ANH = "viet_anh.db"
const val DATABASE_VERSION = 1

class DatabaseAccess(context: Context, databaseName: String) : SQLiteAssetHelper(context,
    databaseName,
    null,
    DATABASE_VERSION
)  {
    private val tableName = databaseName.replace(".db", "")

    fun getAllData() : List<Word> {
        val result = mutableListOf<Word>()
        val query = "SELECT * FROM $tableName LIMIT 200;"
        val cursor = readableDatabase.rawQuery(query, null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val word = cursor.getString(1)
                result.add(Word(id, word, null))
            }while (cursor.moveToNext())
        }

        return result
    }

    fun findWords(word: String) : List<Word> {
        val result = mutableListOf<Word>()
        val text = "%$word%"
        val query = """SELECT * FROM $tableName WHERE word like "$text" LIMIT 200;"""
        val cursor = readableDatabase.rawQuery(query, null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val word = cursor.getString(1)
                result.add(Word(id, word, null))
            }while (cursor.moveToNext())
        }

        return result
    }

    fun getWord(id: Int) : Word {
        val query = "SELECT * FROM $tableName WHERE id = $id;"
        val cursor = readableDatabase.rawQuery(query, null)

        if(cursor.moveToFirst()){
            val id = cursor.getInt(0)
            val word = cursor.getString(1)
            val content = cursor.getString(2)
            return Word(id, word, content)
        }

        throw Throwable("data null")

    }
}