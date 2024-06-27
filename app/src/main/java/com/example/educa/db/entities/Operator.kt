package com.example.educa.db.entities


data class Operator(
val alias: String, // Chiave primaria, corrisponde a 'alias' nella tabella
val first_name: String, // Corrisponde a 'first_name' nella tabella
val last_name: String, // Corrisponde a 'last_name' nella tabella
val role: String // Corrisponde a 'role' nella tabella
// Rimuovi gli attributi non presenti nella tabella, come 'id' ed 'email'
)