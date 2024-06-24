package com.example.educa.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.educa.db.DatabaseHelper
import com.example.educa.db.entities.User


class UserDao(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertUser(user: User) {
        // Implementa la logica per inserire un nuovo utente nel database
    }

    fun getUserById(id: Int): User? {
        // Implementa la logica per recuperare un utente dal database in base all'ID
        return null
    }

    fun getUserByUsername(username: String): User? {
        // Implementa la logica per recuperare un utente dal database in base allo username
        return null
    }

    // Aggiungi altri metodi per aggiornare, eliminare e cercare utenti in base a criteri specifici

    private fun createUserFromCursor(cursor: Cursor): User {
        // Implementa la logica per creare un oggetto User da un cursore
        return User(
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            username = cursor.getString(cursor.getColumnIndexOrThrow("username")),
            email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            // Estrai altri attributi se presenti nella classe User
        )
    }
}