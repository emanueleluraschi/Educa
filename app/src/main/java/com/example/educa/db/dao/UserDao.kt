
// UserDao.kt
package com.example.educa.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.educa.db.DatabaseHelper
import com.example.educa.db.entities.User

class UserDao(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertUser(user: User) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("alias", user.alias)
            put("age", user.age)
            put("first_name", user.first_name)
            put("last_name", user.last_name)
            put("tax_code", user.tax_code)
        }
        db.insert("Users", null, values)
    }

    fun getUserByAlias(alias: String): User? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Users",
            null,
            "alias = ?",
            arrayOf(alias),
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            createUserFromCursor(cursor)
        } else {
            null
        }
    }

    // Aggiungi altri metodi per aggiornare, eliminare e cercare utenti in base a criteri specifici

    private fun createUserFromCursor(cursor: Cursor): User {
        return User(
            alias = cursor.getString(cursor.getColumnIndexOrThrow("alias")),
            age = cursor.getInt(cursor.getColumnIndexOrThrow("age")),
            first_name = cursor.getString(cursor.getColumnIndexOrThrow("first_name")),
            last_name = cursor.getString(cursor.getColumnIndexOrThrow("last_name")),
            tax_code = cursor.getString(cursor.getColumnIndexOrThrow("tax_code"))
        )
    }
    fun getAllUsers(): List<User> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Users",
            null,
            null,
            null,
            null,
            null,
            null)
        val users = mutableListOf<User>()
        while (cursor.moveToNext()) {
            val user = createUserFromCursor(cursor)
            users.add(user)
        }
        return users
    }
}