package com.example.educa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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



        return view
    }


}