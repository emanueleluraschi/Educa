package com.example.educa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val activityCode = MutableLiveData<String>()
    val activityNames = MutableLiveData<List<String>>() // Nuova variabile per la lista di nomi
}