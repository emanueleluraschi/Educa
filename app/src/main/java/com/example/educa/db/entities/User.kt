package com.example.educa.db.entities

data class User(
    val alias: String,
    val age: Int,
    val first_name: String? = null,
    val last_name: String? = null,
    val tax_code: String? = null
)
