package com.example.message.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.bean.Message
import com.example.data.bean.MessageType
import com.example.message.databinding.ItemSystemMessageBinding
import com.example.message.databinding.ViewReceiveMessageItemBinding
import com.example.message.databinding.ViewSendMessageItemBinding

class MessageListAdapter(
    val context: Context,
) : ListAdapter<Message, RecyclerView.ViewHolder>(MessageComparator()) {
    companion object {
        const val ITEM_TYPE_SEND_MESSAGE = 0
        const val ITEM_TYPE_RECEIVE_MESSAGE = 1
        const val ITEM_TYPE_SYSTEM_MESSAGE = 2
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position).type) {
            MessageType.MINE -> ITEM_TYPE_SEND_MESSAGE
            MessageType.OTHER -> ITEM_TYPE_RECEIVE_MESSAGE
            else -> ITEM_TYPE_SYSTEM_MESSAGE
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_TYPE_SEND_MESSAGE -> {
                val sendBinding =
                    ViewSendMessageItemBinding.inflate(LayoutInflater.from(context), parent, false)
                val sendMessageViewHolder = SendMessageViewHolder(sendBinding.root)
                sendMessageViewHolder.binding = sendBinding
                sendMessageViewHolder
            }

            ITEM_TYPE_RECEIVE_MESSAGE -> {
                val receiveBinding =
                    ViewReceiveMessageItemBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false,
                    )
                val sendMessageViewHolder = ReceiveMessageViewHolder(receiveBinding.root)
                sendMessageViewHolder.binding = receiveBinding
                sendMessageViewHolder
            }

            else -> {
                val sendBinding =
                    ItemSystemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
                val systemMessageViewHolder = SystemMessageViewHolder(sendBinding.root)
                systemMessageViewHolder.binding = sendBinding
                systemMessageViewHolder
            }
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val showTimeStamp = isShowTimeStamp(position)
        when (getItemViewType(position)) {
            ITEM_TYPE_SEND_MESSAGE -> {
                (holder as SendMessageViewHolder).bind(getItem(position))
            }

            ITEM_TYPE_RECEIVE_MESSAGE -> {
                (holder as ReceiveMessageViewHolder).bind(getItem(position))
            }

            else -> {
                (holder as SendMessageViewHolder).bind(getItem(position))
            }
        }
    }

    private fun isShowTimeStamp(position: Int): Boolean {
        var showTimeStamp = true
        if (position > 0) {
//            showTimeStamp =
//                !DateUtils.isCloseEnough(messages[position].msgTime, messages[position - 1].msgTime)
        }
        return showTimeStamp
    }

    class SendMessageViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ViewSendMessageItemBinding

        fun bind(message: Message) {
            binding.msg = message
        }
    }

    class ReceiveMessageViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ViewReceiveMessageItemBinding

        fun bind(message: Message) {
            binding.msg = message
        }
    }

    class SystemMessageViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ItemSystemMessageBinding

        fun bind(message: Message) {
            binding.msg = message
        }
    }

    class MessageComparator : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(
            oldItem: Message,
            newItem: Message,
        ): Boolean = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: Message,
            newItem: Message,
        ): Boolean = oldItem.id == newItem.id
    }

    fun addMsg(msg: Message) {
        currentList.add(msg)
        notifyItemInserted(itemCount - 1)
    }
}
