package com.example.roomdatabaseexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.databinding.ItemUserBinding
import com.example.roomdatabaseexample.model.UserUiModel

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private val users= mutableListOf<UserUiModel>()

    fun addUser(data: List<UserUiModel>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding= ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return UserViewHolder(binding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class UserViewHolder(private val userBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(userBinding.root) {

        fun bind(data: UserUiModel) {
            userBinding.user = data
        }
    }
}
