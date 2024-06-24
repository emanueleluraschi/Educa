package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.Adapter.ActivityAdapter
import com.example.educa.R
import com.example.educa.SpacesItemDecoration
import com.example.educa.db.dao.ActivityDao

class Attivita_ritornate : Fragment() {

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

        // Aggiungi i dati al RecyclerView
        val activityDao= ActivityDao(requireContext())
        val activities = activityDao.getAllActivities()
        activityAdapter.updateActivities(activities)


        recyclerView.addItemDecoration(SpacesItemDecoration(10))


        return view
    }
}
