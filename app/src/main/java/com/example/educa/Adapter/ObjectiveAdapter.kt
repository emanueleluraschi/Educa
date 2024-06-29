import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.R
import com.example.educa.SharedViewModel

class ObjectiveAdapter(private val objectives: List<String>,private val sharedViewModel: SharedViewModel) :
    RecyclerView.Adapter<ObjectiveAdapter.ObjectiveViewHolder>() {



    class ObjectiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_obiettivo_nome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectiveViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_obiettivo, parent, false)
        return ObjectiveViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ObjectiveViewHolder, position: Int) {
        val objective = objectives[position]
        holder.textView.text = objective



        // Imposta lo stato iniziale dell'elemento (ad esempio, selezionato o no)
        holder.itemView.isSelected = sharedViewModel.objectiveNames.value?.contains(objective) ?: false
        holder.itemView.setOnClickListener{
            val currentList = sharedViewModel.objectiveNames.value?.toMutableList() ?: mutableListOf()
        if (currentList.contains(objective)) {
            currentList.remove(objective)
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.md_theme_onBackground)) // Colore non selezionato
            holder.textView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.md_theme_onBackground)) // Colore testo non selezionato
            holder.itemView.setBackgroundResource(R.drawable.sfondo_obiettivi)
            Log.d("ObjectiveAdapter", "Objective selected: $currentList")

        } else {
            currentList.add(objective)
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.md_theme_primary)) // Colore selezionato
            holder.textView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.md_theme_background_highContrast))
            holder.itemView.setBackgroundResource(R.drawable.sfondo_obiettivi_selezionati)
            // Aggiorna lo stato dell'elemento (non necessario se gestisci solo il colore di sfondo)
            // holder.itemView.isSelected = selectedObjectives.contains(objective)

            // Qui puoi gestire la logica per gli obiettivi selezionati
            Log.d("ObjectiveAdapter", "Objective selected: $currentList")
        }
            sharedViewModel.objectiveNames.value= currentList
        }
    }

    override fun getItemCount(): Int = objectives.size
}