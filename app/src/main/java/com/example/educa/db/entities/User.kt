package com.example.educa.db.entities

data class User(
    val id: Int? = null, // Potrebbe essere autogenerato dal database
    val username: String,
    val email: String,
    // Aggiungi altri attributi degli utenti se necessario, ad esempio:
    // val name: String? = null,
    // val profilePictureUrl: String? = null
)