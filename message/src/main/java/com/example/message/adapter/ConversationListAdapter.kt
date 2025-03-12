package com.example.message.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.bean.Message
import com.example.data.bean.MessageItem
import com.example.message.databinding.ViewConversationItemBinding

class ConversationListAdapter(
    val context: Context,
    private val conversations: MutableList<MessageItem>,
) : RecyclerView.Adapter<ConversationListAdapter.ConversationListItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ConversationListAdapter.ConversationListItemViewHolder {
        val binding = ViewConversationItemBinding.inflate(LayoutInflater.from(context),parent,false)
        val conversationListItemViewHolder =
            ConversationListItemViewHolder(binding.root)
        conversationListItemViewHolder.binding = binding
        return conversationListItemViewHolder
    }


    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(
        holder: ConversationListAdapter.ConversationListItemViewHolder,
        position: Int,
    ) {
        holder.bind(conversations[position])
    }

    class ConversationListItemViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ViewConversationItemBinding

        fun bind(message: MessageItem){

        }
    }
}
