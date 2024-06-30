package com.example.educa.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.educa.R
import com.example.educa.SharedViewModel
import com.example.educa.db.entities.User


class UserAdapter(
    private var userList: List<User>,
    private val sharedViewModel: SharedViewModel,
    private val onUserDelete: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {







    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val aliasTextView: TextView = itemView.findViewById(R.id.userAliasTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        val ageTextView: TextView = itemView.findViewById(R.id.userAgeTextView)
        val surnameTextView: TextView = itemView.findViewById(R.id.userSurnameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.aliasTextView.text = "Alias: ${user.alias}"
        holder.nameTextView.text = "Nome: ${user.first_name}"
        holder.surnameTextView.text = "Cognome: ${user.last_name}"
        holder.ageTextView.text = "Età: ${user.age}"

        holder.itemView.setOnClickListener {
            sharedViewModel.user.value = user

            it.findNavController().navigate(R.id.action_miei_utenti_bambini_to_dettaglio_utente)
        }
        holder.itemView.setOnLongClickListener { view ->
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.user_context_menu) // Crea un menu con l'opzione "Elimina"
            popupMenu.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.action_delete_user) {
                    onUserDelete(user) // Chiama la callback per eliminare l'utente
                    true
                } else {
                    false
                }
            }
            popupMenu.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    fun updateUsers(newUsers: List<User>) {
        userList = newUsers
        notifyDataSetChanged()
    }
}