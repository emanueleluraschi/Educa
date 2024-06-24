package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.educa.R


class Sign_in_frag : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in_frag, container, false)

        val txtsignup = view.findViewById<TextView>(R.id.Txt_sign_up_tosignin)

        txtsignup.setOnClickListener(){
            view.findNavController().navigate(R.id.action_sign_in_frag_to_sign_up)
        }
        val btnsignin = view.findViewById<Button>(R.id.Btn_sign_in_loggati)

        btnsignin.setOnClickListener(){
            view.findNavController().navigate(R.id.action_sign_in_frag_to_home_ricerca_3)
        }

        return view
    }


}
