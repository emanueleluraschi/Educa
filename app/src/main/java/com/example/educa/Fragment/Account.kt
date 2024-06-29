package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.example.educa.R
import com.example.educa.db.dao.OperatorDao


class Account : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val nightModeSwitch = view.findViewById<Switch>(R.id.Account_modalita_nutturna)
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Imposta il temaをAppCompatDelegate.MODE_NIGHT_YESに設定
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                // Imposta il temaをAppCompatDelegate.MODE_NIGHT_NOに設定
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        val languageSpinner = view.findViewById<Spinner>(R.id.Account_lingua)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.language))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = adapter

        val operatore= OperatorDao(requireContext())
        val firstOperatorDao = operatore.getFirstOperator()

        val nome = firstOperatorDao?.first_name
        val cognome = firstOperatorDao?.last_name
        val email = firstOperatorDao?.alias
        val role = firstOperatorDao?.role

        val nomeTextView = view.findViewById<TextView>(R.id.profile_name)
        val cognomeTextView = view.findViewById<TextView>(R.id.profile_surname)
        val ruoloTextView = view.findViewById<TextView>(R.id.profile_profession)
        val emailTextView = view.findViewById<TextView>(R.id.profile_email)

        nomeTextView.text = nome
        cognomeTextView.text = cognome
        ruoloTextView.text = role
        emailTextView.text = email

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