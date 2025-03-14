package com.example.message.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.bean.MessageItem
import com.example.message.databinding.ViewConversationItemBinding

class ConversationListAdapter(
    val context: Context,
) : ListAdapter<MessageItem, ConversationListAdapter.ConversationListItemViewHolder>(
        MessageItemComparator(),
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ConversationListItemViewHolder {
        val binding =
            ViewConversationItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val conversationListItemViewHolder =
            ConversationListItemViewHolder(binding.root)
        conversationListItemViewHolder.binding = binding
        return conversationListItemViewHolder
    }

    override fun onBindViewHolder(
        holder: ConversationListItemViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class ConversationListItemViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ViewConversationItemBinding

        fun bind(message: MessageItem) {
            binding.msgItem = message
        }
    }

    class MessageItemComparator : DiffUtil.ItemCallback<MessageItem>() {
        override fun areItemsTheSame(
            oldItem: MessageItem,
            newItem: MessageItem,
        ): Boolean = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: MessageItem,
            newItem: MessageItem,
        ): Boolean = oldItem.acceptId == newItem.acceptId
    }
}
