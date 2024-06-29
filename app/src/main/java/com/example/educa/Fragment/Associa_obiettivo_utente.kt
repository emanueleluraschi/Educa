package com.example.educa.Fragment

import ObjectiveAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.educa.R
import com.example.educa.SharedViewModel
import com.example.educa.SpacesItemDecoration
import com.example.educa.db.dao.ObjectiveDao

class Associa_obiettivo_utente : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_associa_obiettivo_utente, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_obiettivi)
            // Imposta un GridLayoutManager con 2 colonne
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        sharedViewModel.objectiveNames.value = emptyList()
        val db = ObjectiveDao(requireContext())
        val objectives = db.getAllObjectives()

        val adapter = ObjectiveAdapter(objectives.map { it.name },sharedViewModel) // Usa ObjectAdapter
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(SpacesItemDecoration(20))

        val bottoneditironto = view.findViewById<Button>(R.id.btn_scegli_attivita)
        bottoneditironto.setOnClickListener(){



            view.findNavController().navigate(R.id.action_associa_obiettivo_utente_to_aggiungi_utente)
        }



        return view
    }
}