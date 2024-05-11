package com.example.educa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class Prova_list_view : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_prova_list_view, container, false)

        val listView = view.findViewById<ListView>(R.id.lst_lista_prova_lista)
        val data = arrayOf("pippo", "paperino", "pluto", "minni", "zio paperone")
        listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data)



        return view
    }
}
