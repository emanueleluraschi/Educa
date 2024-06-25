package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.Adapter.ActivityAdapter
import com.example.educa.R
import com.example.educa.SharedViewModel
import com.example.educa.SpacesItemDecoration
import com.example.educa.db.dao.ActivityDao

class Attivita_ritornate : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var activityAdapter: ActivityAdapter
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attivita_ritornate, container, false)

        recyclerView = view.findViewById(R.id.Recycle_layout_attivita_ritornate)
        activityAdapter = ActivityAdapter(emptyList())
        recyclerView.adapter = activityAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(SpacesItemDecoration(10))

        val numeroAttivitaTextView = view.findViewById<TextView>(R.id.Txt_lista_attivita_numero)

        // Osserva il codice dell'attività nel ViewModel
        viewModel.activityNames.observe(viewLifecycleOwner) { names ->
            if (names != null) {
                val activityDao = ActivityDao(requireContext())
                val activities = names.mapNotNull { activityDao.getActivityByName(it).firstOrNull() } // Recupera i dettagli per ogni nome

                // Aggiorna l'adapter con i dettagli delle attività
                activityAdapter.updateActivities(activities)
                numeroAttivitaTextView.text = "${activities.size}"
            }
        }
        val btn_home = view.findViewById<ImageButton>(R.id.Btn_ricerca)
        val btn_listasalvate= view.findViewById<ImageButton>(R.id.Btn_salvate)
        val btn_account = view.findViewById<ImageButton>(R.id.Btn_account)
        val btn_listautenti= view.findViewById<ImageButton>(R.id.Btn_utenti)

        btn_home.setOnClickListener {
            view.findNavController().navigate(R.id.action_Attivita_ritornate_to_home_ricerca_3)
        }
        btn_account.setOnClickListener {
            view.findNavController().navigate(R.id.action_Attivita_ritornate_to_account)
        }
        btn_listautenti.setOnClickListener {
            view.findNavController().navigate(R.id.action_Attivita_ritornate_to_miei_utenti_bambini)
        }
        btn_listasalvate.setOnClickListener {
            view.findNavController().navigate(R.id.action_Attivita_ritornate_to_lista_attivita)
        }


        return view
    }
}