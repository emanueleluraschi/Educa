package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.educa.R
import com.example.educa.SharedViewModel


class Descrizione_attivita : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_descrizione_attivita, container, false)

        val titoloTextView = view.findViewById<TextView>(R.id.activity_details_title)
        val etaTextView = view.findViewById<TextView>(R.id.activity_details_age)
        val durataTextView = view.findViewById<TextView>(R.id.activity_details_duration)
        val modalitaTextView = view.findViewById<TextView>(R.id.activity_details_modality)
        val descrizioneTextView = view.findViewById<TextView>(R.id.activity_details_description)

        SharedViewModel.selectedActivity.observe(viewLifecycleOwner) { activity ->
        activity?.let {
            titoloTextView.text = it.name
            etaTextView.text = "età: "+ it.age.toString()
            durataTextView.text ="durata: " +it.duration.toString()+ " minuti"
            modalitaTextView.text = "modalità: "+ when {
                it.isForSingle && it.isForGroup -> "Singolo e Gruppo"
                it.isForSingle -> "Singolo"
                it.isForGroup -> "Gruppo"
                else -> " "
            }
            descrizioneTextView.text = it.descriptionLong
            }
        }

        return view
    }



}