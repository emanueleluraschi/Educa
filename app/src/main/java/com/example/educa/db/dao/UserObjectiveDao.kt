package com.example.educa.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.educa.db.DatabaseHelper

class UserObjectiveDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insert(userAlias: String, objectiveName: String) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_alias", userAlias)
            put("objective_name", objectiveName)
        }
        db.insert("User_Objectives", null, values)
    }
    fun getUserObjectives(userAlias: String): List<String> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "User_Objectives",
            arrayOf("objective_name"),
            "user_alias = ?",
            arrayOf(userAlias),
            null,
            null,
            null
        )
        val objectives = mutableListOf<String>()
        while (cursor.moveToNext()) {
            objectives.add(cursor.getString(cursor.getColumnIndexOrThrow("objective_name")))
        }
        cursor.close()
        db.close()
        return objectives
    }

    // Aggiungi altri metodi per operazioni come delete, update, query se necessario
}