package com.example.educa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class Welcome : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)


        val bottonesignin = view.findViewById<Button>(R.id.Btn_welcome_Sign_in)
        val bottonesignup = view.findViewById<Button>(R.id.Btn_welcome_Sign_up)

        bottonesignin.setOnClickListener(){
            view.findNavController().navigate(R.id.action_welcome_to_sign_in_frag)
        }

        bottonesignup.setOnClickListener(){
            view.findNavController().navigate(R.id.action_welcome_to_sign_up)
        }

        return view
    }

}