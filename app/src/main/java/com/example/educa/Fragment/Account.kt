package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Switch
import androidx.navigation.findNavController
import com.example.educa.R


class Account : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val nightModeSwitch = view.findViewById<Switch>(R.id.Account_modalita_nutturna)
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {}




        val btn_home = view.findViewById<ImageButton>(R.id.Btn_ricerca)
        val btn_listasalvate= view.findViewById<ImageButton>(R.id.Btn_salvate)
        val btn_account = view.findViewById<ImageButton>(R.id.Btn_account)
        val btn_listautenti= view.findViewById<ImageButton>(R.id.Btn_utenti)


        btn_home.setOnClickListener {
            view.findNavController().navigate(R.id.action_account_to_home_ricerca_3)
        }
        btn_listautenti.setOnClickListener {
            view.findNavController().navigate(R.id.action_account_to_miei_utenti_bambini)
        }
        btn_listasalvate.setOnClickListener {
            view.findNavController().navigate(R.id.action_account_to_lista_attivita)
        }
        return view
    }
}