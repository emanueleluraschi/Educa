package com.example.educa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.findNavController


class Lista_attivita : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lista_attivita, container, false)


        val btn_home = view.findViewById<ImageButton>(R.id.Btn_ricerca)
        val btn_listasalvate= view.findViewById<ImageButton>(R.id.Btn_salvate)
        val btn_account = view.findViewById<ImageButton>(R.id.Btn_account)
        val btn_listautenti= view.findViewById<ImageButton>(R.id.Btn_utenti)


        btn_home.setOnClickListener {
            view.findNavController().navigate(R.id.action_lista_attivita_to_home_ricerca_3)
        }
        btn_account.setOnClickListener {
            view.findNavController().navigate(R.id.action_lista_attivita_to_account)
        }
        btn_listautenti.setOnClickListener {
            view.findNavController().navigate(R.id.action_lista_attivita_to_miei_utenti_bambini)
        }
        return view
    }

}