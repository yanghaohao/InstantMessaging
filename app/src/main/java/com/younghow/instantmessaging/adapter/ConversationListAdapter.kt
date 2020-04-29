package com.younghow.instantmessaging.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMConversation
import com.younghow.instantmessaging.ui.activity.ChatActivity
import com.younghow.instantmessaging.weight.ConversationListItemView
import org.jetbrains.anko.startActivity

class ConversationListAdapter(val context: Context, private val conversations:MutableList<EMConversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ConversationListItemViewHolder(ConversationListItemView(context))

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationListItemView = holder.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])

        conversationListItemView.setOnClickListener{context.startActivity<ChatActivity>("username" to conversations[position])}
    }

    class ConversationListItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
}