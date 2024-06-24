package com.example.educa.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.Adapter.ActivityAdapter
import com.example.educa.R
import com.example.educa.SpacesItemDecoration
import com.example.educa.db.dao.ActivityDao

class Attivita_ritornate : Fragment() {

val args : Bundle? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_attivita_ritornate, container, false)

        recyclerView = view.findViewById(R.id.Recycle_layout_attivita_ritornate)

        activityAdapter = ActivityAdapter(emptyList())
        recyclerView.adapter = activityAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(SpacesItemDecoration(10))

        // Ottieni la query dagli argomenti
        val query = arguments?.getString("query") ?: ""

        Log.d("cavolo", "Query: $query")

        // Aggiungi i dati al RecyclerView, filtrando in base alla query
        val activityDao = ActivityDao(requireContext())
        val activities = if (query.isBlank()) {
            activityDao.getAllActivities() // Nessuna query, mostra tutte le attività
        } else {
            activityDao.searchActivities(query) // Filtra le attività in base alla query
        }
        activityAdapter.updateActivities(activities)

        val numeroAttivitaTextView = view.findViewById<TextView>(R.id.Txt_lista_attivita_numero)
        numeroAttivitaTextView.text = "${activities.size}"



        return view
    }
}
