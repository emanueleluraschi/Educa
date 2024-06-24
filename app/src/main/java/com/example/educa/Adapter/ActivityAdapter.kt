package com.example.educa.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.R
import com.example.educa.ViewHolder.ActivityViewHolder
import com.example.educa.db.entities.Activity

class ActivityAdapter(private var activities: List<Activity>) : // Sostituisci "Activity" con la tua classe
    RecyclerView.Adapter<ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.attivita_view, parent, false) // Assicurati che questo sia il nome corretto del tuo layout XML
        return ActivityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.titoloTextView.text = activity.name
        holder.durataTextView.text = "Durata: ${activity.duration ?: "N/A"}"

        holder.spiegazioneTextView.text = activity.descriptionShort ?: "" // Usa descriptionLong se disponibile, altrimenti una stringa vuota
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    fun updateActivities(newActivities: List<Activity>) { // Sostituisci "Activity" con la tua classe
        activities = newActivities
        notifyDataSetChanged()
    }
}