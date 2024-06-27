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
            put("alias", operator.alias)
            put("first_name", operator.first_name)
            put("last_name", operator.last_name)
            put("role", operator.role)
        }
        db.insert("Operators", null, values)
        db.close()
    }

    fun getOperatorByAlias(alias: String): Operator? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Operators",
            null,
            "alias = ?",
            arrayOf(alias),
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
            alias = cursor.getString(cursor.getColumnIndexOrThrow("alias")),
            first_name = cursor.getString(cursor.getColumnIndexOrThrow("first_name")),
            last_name = cursor.getString(cursor.getColumnIndexOrThrow("last_name")),
            role = cursor.getString(cursor.getColumnIndexOrThrow("role"))
        )
    }
}