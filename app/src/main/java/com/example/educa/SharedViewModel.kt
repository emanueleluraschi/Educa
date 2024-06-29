package com.example.educa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.educa.db.entities.Activity
object SharedViewModel : ViewModel() {

    val activityNames = MutableLiveData<List<String>>() // Nuova variabile per la lista di nomi delle attiività
    val selectedActivity  = MutableLiveData<Activity>()
    val objectiveNames = MutableLiveData<List<String>>() // Nuova variabile per la lista di nomi degli obiettivi
    val toastNames = MutableLiveData<List<String>>()
}