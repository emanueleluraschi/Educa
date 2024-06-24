package com.example.educa.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.educa.db.DatabaseHelper
import com.example.educa.db.entities.Operator


class OperatorDao(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertOperator(operator: Operator) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("username", operator.username)
            put("email", operator.email)
            // Aggiungi altri attributi se presenti nella classe Operator
        }
        db.insert("Operators", null, values)
        db.close()
    }

    fun getOperatorById(id: Int): Operator? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Operators",
            null,
            "id = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        var operator: Operator? = null
        if (cursor.moveToFirst()) {
            operator = createOperatorFromCursor(cursor)
        }
        cursor.close()
        db.close()
        return operator
    }

    fun getOperatorByUsername(username: String): Operator? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Operators",
            null,
            "username = ?",
            arrayOf(username),
            null,
            null,
            null
        )
        var operator: Operator? = null
        if (cursor.moveToFirst()) {
            operator = createOperatorFromCursor(cursor)
        }
        cursor.close()
        db.close()
        return operator
    }

    // Aggiungi altri metodi per aggiornare, eliminare e cercare operatori in base a criteri specifici

    private fun createOperatorFromCursor(cursor: Cursor): Operator {
        return Operator(
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            username = cursor.getString(cursor.getColumnIndexOrThrow("username")),
            email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            // Estrai altri attributi se presenti nella classe Operator
        )
    }
}