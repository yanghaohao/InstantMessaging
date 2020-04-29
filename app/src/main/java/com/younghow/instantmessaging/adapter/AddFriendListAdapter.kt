package com.younghow.instantmessaging.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.younghow.instantmessaging.data.AddFrendItem
import com.younghow.instantmessaging.weight.AddFriendListItemView
import java.util.zip.Inflater

class AddFriendListAdapter(val context: Context,val list: List<AddFrendItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = AddFriendListItemViewHolder(AddFriendListItemView(context))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val addFriendListItemView = holder.itemView as AddFriendListItemView
        addFriendListItemView.bindView(list.get(position))
    }

    class AddFriendListItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }
}