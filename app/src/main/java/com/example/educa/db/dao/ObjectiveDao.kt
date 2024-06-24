package com.example.educa.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.educa.db.DatabaseHelper
import com.example.educa.db.entities.Objective

class ObjectiveDao(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertObjective(objective: Objective) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", objective.name)
            // Aggiungi altri attributi se presenti nella classe Objective
        }
        db.insert("Objectives", null, values)
        db.close()
    }

    fun getAllObjectives(): List<Objective> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Objectives", null, null, null, null, null, null)
        val objectives = mutableListOf<Objective>()
        while (cursor.moveToNext()) {
            objectives.add(createObjectiveFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return objectives
    }

    // Aggiungi altri metodi per aggiornare, eliminare e cercare obiettivi in base a criteri specifici

    private fun createObjectiveFromCursor(cursor: Cursor): Objective {
        return Objective(
            name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            // Estrai altri attributi se presenti nella classe Objective
        )
    }
}