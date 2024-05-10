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
        val data = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        listView.adapter  = ArrayAdapter(requireContext(), R.layout.elementodilista, data)
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                requireContext(),
                "Item selezionato: ${data[position]}",
                Toast.LENGTH_SHORT
            ).show()

        }

        return view
    }
}
