package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.educa.R
import com.example.educa.db.dao.UserDao
import com.example.educa.db.entities.User


class Aggiungi_utente : Fragment() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDao = UserDao(requireContext()) // Inizializza il DAO
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_aggiungi_utente, container, false)

        val nomeInput = view.findViewById<EditText>(R.id.nomeInput)
        val cognomeInput = view.findViewById<EditText>(R.id.cognomeInput)
        val etaInput = view.findViewById<EditText>(R.id.etaInput)
        val aliasInput = view.findViewById<EditText>(R.id.aliasInput)
        val aggiungiUtenteButton = view.findViewById<Button>(R.id.aggiungiUtenteButton)

        val associa_obiettivo_utente = view.findViewById<Button>(R.id.Btn_associa_utente_aggiungi_obiettivo)

        associa_obiettivo_utente.setOnClickListener {
            view.findNavController().navigate(R.id.action_aggiungi_utente_to_associa_obiettivo_utente)
        }

        aggiungiUtenteButton.setOnClickListener {
            val nome = nomeInput.text.toString()
            val cognome = cognomeInput.text.toString()
            val eta = etaInput.text.toString().toInt()
            val alias = aliasInput.text.toString()
            // Aggiungi il nuovo utente al database
            val nuovoUtente = User(
                alias = alias,
                age = eta,
                first_name = nome,
                last_name = cognome)
            userDao.insertUser(nuovoUtente)

            // Mostra un messaggio di successo
            Toast.makeText(requireContext(), "Utente aggiunto con successo!", Toast.LENGTH_SHORT).show()
            // Resetta i campi dell'interfaccia utente
            nomeInput.text.clear()
            cognomeInput.text.clear()
            etaInput.text.clear()
            aliasInput.text.clear()

        }

        return view
    }

}