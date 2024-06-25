package com.example.educa.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.R


class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titoloTextView: TextView = itemView.findViewById(R.id.Txt_attivita_titolo)
    val durataTextView: TextView = itemView.findViewById(R.id.Txt_attivita_durata)
    val descrizioneTextView: TextView = itemView.findViewById(R.id.Txt_attivita_descrizione_lunga)
    val spiegazioneTextView: TextView = itemView.findViewById(R.id.Txt_attivita_spiegazione_breve)
    val starImage: ImageView = itemView.findViewById(R.id.img_immagine)
}