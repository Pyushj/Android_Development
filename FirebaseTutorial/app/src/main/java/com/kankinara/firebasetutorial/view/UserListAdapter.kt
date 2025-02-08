package com.kankinara.firebasetutorial.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kankinara.firebasetutorial.R
import com.kankinara.firebasetutorial.databinding.ItemUserBinding
import com.kankinara.firebasetutorial.model.User


class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val userList = mutableListOf<User>()

    fun setUserList(list: List<User>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemUserBinding>(inflater, R.layout.item_user, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val binding = holder.binding
        val user = userList[position]

        binding.name.text = user.name
        binding.age.text = user.age.toString()
        binding.email.text = user.email
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)
}