package com.example.educa.Adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.R
import com.example.educa.SharedViewModel
import com.example.educa.ViewHolder.ActivityViewHolder
import com.example.educa.db.dao.ActivityDao
import com.example.educa.db.entities.Activity

class ActivityAdapter(private var activities: List<Activity>) : // Sostituisci "Activity" con la tua classe
    RecyclerView.Adapter<ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.attivita_view,
                parent,
                false
            ) // Assicurati che questo sia il nome corretto del tuo layout XML
        return ActivityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.titoloTextView.text = activity.name
        holder.durataTextView.text = "Durata: ${activity.duration ?: "N/A"}"


        holder.spiegazioneTextView.text = activity.descriptionShort
            ?: "" // Usa descriptionLong se disponibile, altrimenti una stringa vuota
        holder.itemView.setOnClickListener {
            Log.d("ActivityAdapter", "Descrizione cliccata per ${activity.name}")
            SharedViewModel.selectedActivity.value = activity
            it.findNavController().navigate(R.id.action_Attivita_ritornate_to_descrizione_attivita)
        }
        holder.starImage.setImageResource(if (activity.isFavorite) R.drawable.baseline_star_48 else R.drawable.baseline_star_border_48)
        holder.starImage.setOnClickListener {
            // Gestisci il clic su un pulsante di star
            val activityDao = ActivityDao(holder.itemView.context)
            val isFavorite = activityDao.isFavorite(activity.name)
            activityDao.setFavorite(activity.name, !isFavorite)
            holder.starImage.setImageResource(if (isFavorite) R.drawable.baseline_star_border_48 else R.drawable.baseline_star_48)
            activities = activities.toMutableList().apply {
                this[position] = activity.copy(isFavorite = !isFavorite)
            }
        }
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    fun updateActivities(newActivities: List<Activity>) { // Sostituisci "Activity" con la tua classe
        activities = newActivities
        notifyDataSetChanged()
    }
}