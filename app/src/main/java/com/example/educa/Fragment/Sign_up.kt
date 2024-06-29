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
import androidx.navigation.findNavController
import com.example.educa.R
import com.example.educa.db.dao.OperatorDao
import com.example.educa.db.entities.Operator
import com.google.firebase.auth.FirebaseAuth

class Sign_up : Fragment() {

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        auth = FirebaseAuth.getInstance()

        val email = view.findViewById<EditText>(R.id.Edt_sign_up_email)
        val password = view.findViewById<EditText>(R.id.Edt_sign_up_password)
        val nome = view.findViewById<EditText>(R.id.Edt_sign_up_nome) // Aggiunto riferimento al campo nome
        val btnSignUp = view.findViewById<Button>(R.id.Btn_sign_up_loggati) // Pulsante per il sign-up
        val txtSignIn = view.findViewById<TextView>(R.id.Txt_sign_up_tosignin)
        val ruolo = view.findViewById<EditText>(R.id.Edt_sign_up_ruolo) //

        btnSignUp.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val nomeText = nome.text.toString().trim()
            val ruoloText = ruolo.text.toString().trim()

            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(requireActivity()) { task ->
                    if(task.isSuccessful){
                        val user = auth.currentUser

                        Toast.makeText(requireContext(), "Registrazione effettuata con successo", Toast.LENGTH_SHORT).show()
                        val newOperator = Operator(
                            alias = emailText,
                            first_name = nomeText,
                            last_name = "",
                            role = ruoloText,

                        )
                        val opratorDao = OperatorDao(requireContext())
                        opratorDao.insertOperator(newOperator)

                        view.findNavController().navigate(R.id.action_sign_up_to_sign_in_frag)
                    }
                    else{
                        val error =  "Errore durante sign_up"
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(requireContext(), "Compila tutti i campi", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

}