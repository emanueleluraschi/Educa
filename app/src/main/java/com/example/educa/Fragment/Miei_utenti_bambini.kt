package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.Adapter.UserAdapter
import com.example.educa.R
import com.example.educa.SharedViewModel
import com.example.educa.db.dao.UserDao
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.educa.SpacesItemDecoration



class Miei_utenti_bambini : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userDao: UserDao
    private lateinit var userAdapter: UserAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDao = UserDao(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_miei_utenti_bambini, container, false)

        recyclerView= view.findViewById<RecyclerView>(R.id.Recycleview_utenti)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UserAdapter(userDao.getAllUsers(), sharedViewModel)
        recyclerView.adapter = userAdapter



        val btn_home = view.findViewById<ImageButton>(R.id.Btn_ricerca)
        val btn_listasalvate= view.findViewById<ImageButton>(R.id.Btn_salvate)
        val btn_account = view.findViewById<ImageButton>(R.id.Btn_account)
        val btn_listautenti= view.findViewById<ImageButton>(R.id.Btn_utenti)


        val floatingbottonaggiungiutente = view.findViewById<FloatingActionButton>(R.id.F_btn_aggiungi_utente)
        floatingbottonaggiungiutente.setOnClickListener {
            view.findNavController().navigate(R.id.action_miei_utenti_bambini_to_aggiungi_utente)
        }


        btn_home.setOnClickListener {
            view.findNavController().navigate(R.id.action_miei_utenti_bambini_to_home_ricerca_3)
        }
        btn_account.setOnClickListener {
            view.findNavController().navigate(R.id.action_miei_utenti_bambini_to_account)
        }
        btn_listasalvate.setOnClickListener {
            view.findNavController().navigate(R.id.action_miei_utenti_bambini_to_lista_attivita)
        }


        recyclerView.addItemDecoration(SpacesItemDecoration(20))


        return view
    }


}