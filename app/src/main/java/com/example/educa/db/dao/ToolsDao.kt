package com.example.educa.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.educa.db.DatabaseHelper
import com.example.educa.db.entities.Tools

class ToolsDao(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertTool(tool: Tools) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", tool.name)
            // Aggiungi altri attributi se presenti nella classe Tools
        }
        db.insert("Tools", null, values)
        db.close()
    }

    fun getAllTools(): List<Tools> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Tools", null, null, null, null, null, null)
        val tools = mutableListOf<Tools>()
        while (cursor.moveToNext()) {
            tools.add(createToolFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return tools
    }

    // Aggiungi altri metodi per aggiornare, eliminare e cercare strumenti in base a criteri specifici

    private fun createToolFromCursor(cursor: Cursor): Tools {
        return Tools(
            name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            // Estrai altri attributi se presenti nella classe Tools
        )
    }
}