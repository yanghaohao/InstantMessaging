package com.example.contacts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ItemContactItemBinding
import com.example.data.bean.Friend

class ContactListAdapter(
    private val context: Context,
) : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {
    private lateinit var friends: List<Friend>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactListViewHolder {
        val binding = ItemContactItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val viewHolder = ContactListViewHolder(binding.root)
        viewHolder.mBinding = binding
        return viewHolder
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(
        holder: ContactListViewHolder,
        position: Int,
    ) {
        holder.bind(friends[position])
    }

    class ContactListViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var mBinding: ItemContactItemBinding

        fun bind(friend: Friend) {
        }
    }
}
