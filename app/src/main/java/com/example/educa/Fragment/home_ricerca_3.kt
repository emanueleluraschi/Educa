package com.example.educa.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner

import androidx.navigation.findNavController
import com.example.educa.R
import com.example.educa.db.dao.ActivityDao
import com.example.educa.db.dao.ObjectiveDao
import com.example.educa.db.dao.ToolsDao
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
            view.findNavController().navigate(R.id.action_home_ricerca_3_to_Attivita_ritornate)
        }

        val btn_home = view.findViewById<ImageButton>(R.id.Btn_ricerca)
        val btn_listasalvate= view.findViewById<ImageButton>(R.id.Btn_salvate)
        val btn_account = view.findViewById<ImageButton>(R.id.Btn_account)
        val btn_listautenti= view.findViewById<ImageButton>(R.id.Btn_utenti)


        btn_listasalvate.setOnClickListener {
            view.findNavController().navigate(R.id.action_home_ricerca_3_to_lista_attivita)
        }
        btn_account.setOnClickListener {
            view.findNavController().navigate(R.id.action_home_ricerca_3_to_account)
        }
        btn_listautenti.setOnClickListener {
            view.findNavController().navigate(R.id.action_home_ricerca_3_to_miei_utenti)
        }



/*

        val opzionieta = arrayOf("2", "3", "4", "5")
        val adaptereta = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opzionieta)


        adaptereta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnereta = view.findViewById<Spinner>(R.id.Spn_ricerca_eta)
         spinnereta.prompt = "Seleziona l'età"

        spinnereta.adapter = adaptereta


*/
        // 2. Recupera le età distinte dal database
        val activityDao = ActivityDao(requireContext())
        val ages =listOf( "Età")+ activityDao.getDistinctAges()
        // 3. Crea un ArrayAdapter con le età
        val adapterEta = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ages)
        adapterEta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // 4. Imposta l'adapter sullo Spinner
        val spinnerEta = view.findViewById<Spinner>(R.id.Spn_ricerca_eta)
        spinnerEta.adapter = adapterEta


        val opzioniGruppoSingolo = arrayOf("Modalità","Singolo", "Gruppo")
        val adapterGruppoSingolo = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opzioniGruppoSingolo)
        adapterGruppoSingolo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerGruppoSingolo = view.findViewById<Spinner>(R.id.Spn_ricerca_gruppo_singolo)
        spinnerGruppoSingolo.adapter = adapterGruppoSingolo

        val toolsDao = ToolsDao(requireContext())
        //val tools = toolsDao.getAllTools()
        //val toolNames = tools.map { it.name }
        //adapter per spinner tool
        val toolNames = listOf("Materiale") + toolsDao.getAllTools().map { it.name } // Aggiungi "Materiale" all'inizio
        val adapterTools = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, toolNames)
        adapterTools.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerTools = view.findViewById<Spinner>(R.id.Spn_ricerca_tools)
        spinnerTools.adapter = adapterTools


        /*val opzioniobiettiviti = arrayOf("obiettivo","attenzione","propriocezione", "consapevolezza")
        val adapterobiettivi = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,opzioniobiettiviti)
        adapterobiettivi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerobiettivi= view.findViewById<Spinner>(R.id.Spn_ricerca_obiettivo)
        spinnerobiettivi.adapter = adapterobiettivi

*/
        // Recupera gli obiettivi dal database
        val objectiveDao = ObjectiveDao(requireContext())
        val objectives = objectiveDao.getAllObjectives()
        val objectiveNames = listOf("Obiettivo") + objectives.map { it.name }

        // Crea un ArrayAdapter con i nomi degli obiettivi
        val adapterObiettivi = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, objectiveNames)
        adapterObiettivi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Imposta l'adapter sullo Spinner
        val spinnerObiettivi = view.findViewById<Spinner>(R.id.Spn_ricerca_obiettivo)
        spinnerObiettivi.adapter = adapterObiettivi



        return view
    }


}