package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.educa.R
import com.google.firebase.auth.FirebaseAuth


class Sign_in_frag : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_in_frag, container, false)

        auth = FirebaseAuth.getInstance()

        val email = view?.findViewById<EditText>(R.id.Edt_sign_up_email)

        val password = view?.findViewById<EditText>(R.id.Edt_sign_up_password)

        val txtPasswordDimenticata = view.findViewById<TextView>(R.id.txt_passdimenticata)
        txtPasswordDimenticata.setOnClickListener {
            val emailText = email?.text.toString().trim()
            if (emailText.isNotEmpty()) {
                auth.sendPasswordResetEmail(emailText)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Email di reset della password inviata a $emailText",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val errorMessage = task.exception?.message
                                ?: "Errore durante l'invio dell'email di reset"
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(),
                    "Inserisci l'email per resettare la password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Inflate the layout for this fragment

        val txtsignup = view.findViewById<TextView>(R.id.Txt_sign_up_tosignin)

        txtsignup.setOnClickListener() {
            view.findNavController().navigate(R.id.action_sign_in_frag_to_sign_up)
        }
        val btnsignin = view.findViewById<Button>(R.id.Btn_sign_up_loggati)

        btnsignin.setOnClickListener() {
            val emailtext = email?.text.toString()
            val passwordtext = password?.text.toString()
            if (emailtext.isNotEmpty() && passwordtext.isNotEmpty()) {
                auth.signInWithEmailAndPassword(emailtext, passwordtext)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser

                            if (user != null) {
                                view.findNavController()
                                    .navigate(R.id.action_sign_in_frag_to_home_ricerca_3,null,
                                        NavOptions.Builder()
                                        .setPopUpTo(R.id.nav_graph, inclusive = true)
                                        .build())
                            } else {
                                (Toast.makeText(requireContext(), "Errore", Toast.LENGTH_SHORT)
                                    .show())
                            }
                        } else {
                            Toast.makeText(
                                requireContext(), "Inserisci o ricontrolla email e password",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }
            } else {
                Toast.makeText(
                    requireContext(), "Inserisci o ricontrolla email e password",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
        return view

    }

}
