package com.example.educa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educa.db.entities.Activity
object SharedViewModel : ViewModel() {
    val activityCode = MutableLiveData<String>()
    val activityNames = MutableLiveData<List<String>>() // Nuova variabile per la lista di nomi
    val selectedActivity  = MutableLiveData<Activity>()
}