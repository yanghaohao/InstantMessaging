package com.example.contacts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ViewAddFriendItemBinding
import com.example.data.bean.Friend

class AddFriendListAdapter(
    val context: Context,
) : RecyclerView.Adapter<AddFriendListAdapter.AddFriendListItemViewHolder>() {
    lateinit var friends: List<Friend>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AddFriendListItemViewHolder {
        val binding = ViewAddFriendItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val viewHolder = AddFriendListItemViewHolder(binding.root)
        viewHolder.mBinding = binding
        return viewHolder
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(
        holder: AddFriendListItemViewHolder,
        position: Int,
    ) {
        holder.bind(friends[position])
    }

    class AddFriendListItemViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var mBinding: ViewAddFriendItemBinding

        fun bind(friend: Friend) {
        }
    }
}
