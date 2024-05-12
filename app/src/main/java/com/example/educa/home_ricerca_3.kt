package com.example.educa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class home_ricerca_3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_ricerca_3, container, false)

        val btn_ricerca = view.findViewById<FloatingActionButton>(R.id.Flt_home_ricerca_a_ricerca)

        btn_ricerca.setOnClickListener {
            view.findNavController().navigate(R.id.action_home_ricerca_3_to_prova_list_view)
        }

        val opzionieta = arrayOf("2", "3", "4", "5")
        val adaptereta = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opzionieta)


        adaptereta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnereta = view.findViewById<Spinner>(R.id.Spn_ricerca_eta)
         spinnereta.prompt = "Seleziona l'età"

        spinnereta.adapter = adaptereta




        val opzioniobiettiviti = arrayOf("attenzione","propriocezione", "consapevolezza")
        val adapterobiettivi = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,opzioniobiettiviti)
        adapterobiettivi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerobiettivi= view.findViewById<Spinner>(R.id.Spn_ricerca_obiettivo)
        spinnerobiettivi.adapter = adapterobiettivi



        return view
    }


}