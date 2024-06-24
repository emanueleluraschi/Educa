package com.example.educa.db.entities


data class Activity(
    val name: String,
    val age: Int,
    val isFavorite: Boolean,
    val isForGroup: Boolean,
    val isForSingle: Boolean,
    val descriptionShort: String,
    val descriptionLong: String? = null,
    val duration: Int? = null,
    val variants: String? = null,
    val howToDoIt: String? = null
)