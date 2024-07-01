package com.example.educa.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.R
import com.example.educa.SharedViewModel
import com.example.educa.db.dao.ActivityDao
import com.example.educa.db.entities.Activity


class SuggestedActivityAdapter(
    private var activities: List<Activity>


) :
    RecyclerView.Adapter<SuggestedActivityAdapter.SuggestedActivityViewHolder>() {




    class SuggestedActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titoloTextView: TextView = itemView.findViewById(R.id.Txt_attivita_titolo)
        val durataTextView: TextView = itemView.findViewById(R.id.Txt_attivita_durata)
        val spiegazioneTextView: TextView =
            itemView.findViewById(R.id.Txt_attivita_spiegazione_breve)
        val starImage: ImageView = itemView.findViewById(R.id.img_immagine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedActivityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.attivita_view, parent, false)
        return SuggestedActivityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuggestedActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.titoloTextView.text = activity.name
        holder.durataTextView.text = "Durata: ${activity.duration ?: "N/A"}"
        holder.spiegazioneTextView.text = activity.descriptionShort ?: ""

        holder.itemView.setOnClickListener {
            SharedViewModel.selectedActivity.value = activity

            it.findNavController().navigate(R.id.action_dettaglio_utente_to_descrizione_attivita)
        }

        holder.starImage.setImageResource(if (activity.isFavorite) R.drawable.baseline_star_48 else R.drawable.baseline_star_border_48)
        holder.starImage.setOnClickListener {
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

    fun updateActivities(newActivities: List<Activity>) {
        activities = newActivities
        notifyDataSetChanged()
    }
}