package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.Adapter.ActivityAdapter
import com.example.educa.Adapter.SuggestedActivityAdapter
import com.example.educa.R
import com.example.educa.SharedViewModel
import com.example.educa.SpacesItemDecoration
import com.example.educa.db.dao.ActivityDao
import com.example.educa.db.dao.UserObjectiveDao


class Dettaglio_utente : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var userObjectiveDao: UserObjectiveDao
    private lateinit var suggestedActivityAdapter: SuggestedActivityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userObjectiveDao = UserObjectiveDao(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dettaglio_utente, container, false)
        val user = sharedViewModel.user.value // Ottieni l'utente dalla SharedViewModel
        val userObjectives = user?.let { userObjectiveDao.getUserObjectives(it.alias) } ?: emptyList()
        val activityDao = ActivityDao(requireContext())
        val suggestedActivities = activityDao.getActivitiesByObjectives(userObjectives)
        suggestedActivityAdapter = SuggestedActivityAdapter(suggestedActivities)


        val recyclerView = view.findViewById<RecyclerView>(R.id.vista_utente_recyview_suggested_activities)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val activityAdapter = ActivityAdapter(emptyList()) // Inizializza con una lista vuota
        recyclerView.adapter = suggestedActivityAdapter
        recyclerView.addItemDecoration(SpacesItemDecoration(10))
        val userNameTextView = view.findViewById<TextView>(R.id.vista_utente_tv_user_name)
        val userSurnameTextView = view.findViewById<TextView>(R.id.vista_utente_tv_user_surname)
        val userAgeTextView = view.findViewById<TextView>(R.id.vista_utente_tv_user_age)

        sharedViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                userNameTextView.text = "Nome: ${it.first_name}"
                userSurnameTextView.text = "Cognome: ${it.last_name}"
                userAgeTextView.text = "Età: ${it.age}"
            }
        }




        return view
    }


}