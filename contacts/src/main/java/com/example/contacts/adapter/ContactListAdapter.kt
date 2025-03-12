package com.example.contacts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ItemContactItemBinding
import com.example.data.bean.Friend

class ContactListAdapter(
    private val context: Context,
) : ListAdapter<Friend,ContactListAdapter.ContactListViewHolder>(FriendComparator()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactListViewHolder {
        val binding = ItemContactItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val viewHolder = ContactListViewHolder(binding.root)
        viewHolder.mBinding = binding
        return viewHolder
    }

    override fun onBindViewHolder(
        holder: ContactListViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class ContactListViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var mBinding: ItemContactItemBinding

        fun bind(friend: Friend) {
        }
    }

    class FriendComparator : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(
            oldItem: Friend,
            newItem: Friend,
        ): Boolean = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: Friend,
            newItem: Friend,
        ): Boolean = oldItem.id == newItem.id
    }
}
