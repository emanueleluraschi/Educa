package com.example.educa.db.entities

data class Operator(
    val id: Int? = null, // Potrebbe essere autogenerato dal database
    val username: String,
    val email: String,
    // Aggiungi altri attributi degli operatori se necessario, ad esempio:
    // val name: String? = null,
    // val role: String? = null, // Ruolo dell'operatore
    // val profilePictureUrl: String? = null
)