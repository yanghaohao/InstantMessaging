package com.example.message.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.bean.Message
import com.example.data.bean.MessageType
import com.example.message.databinding.ViewReceiveMessageItemBinding
import com.example.message.databinding.ViewSendMessageItemBinding

class MessageListAdapter(
    val context: Context,
    val messages: List<Message>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_TYPE_SEND_MESSAGE = 0
        const val ITEM_TYPE_RECEIVE_MESSAGE = 1
        const val ITEM_TYPE_SYSTEM_MESSAGE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when(messages[position].type){
            MessageType.MINE -> ITEM_TYPE_SEND_MESSAGE
            MessageType.OTHER -> ITEM_TYPE_RECEIVE_MESSAGE
            else -> ITEM_TYPE_SYSTEM_MESSAGE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_TYPE_SEND_MESSAGE -> {
                val sendBinding = ViewSendMessageItemBinding.inflate(LayoutInflater.from(context),parent,false)
                val sendMessageViewHolder = SendMessageViewHolder(sendBinding.root)
                sendMessageViewHolder.binding = sendBinding
                sendMessageViewHolder
            }
            ITEM_TYPE_RECEIVE_MESSAGE -> {
                val receiveBinding = ViewReceiveMessageItemBinding.inflate(LayoutInflater.from(context),parent,false)
                val sendMessageViewHolder = ReceiveMessageViewHolder(receiveBinding.root)
                sendMessageViewHolder.binding = receiveBinding
                sendMessageViewHolder
            }
            else -> {
                val sendBinding = ViewSendMessageItemBinding.inflate(LayoutInflater.from(context),parent,false)
                val sendMessageViewHolder = SendMessageViewHolder(sendBinding.root)
                sendMessageViewHolder.binding = sendBinding
                sendMessageViewHolder
            }
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val showTimeStamp = isShowTimeStamp(position)
        when(getItemViewType(position)){
            ITEM_TYPE_SEND_MESSAGE -> {
                (holder as SendMessageViewHolder).bind(messages[position])
            }

            ITEM_TYPE_RECEIVE_MESSAGE -> {
                (holder as ReceiveMessageViewHolder).bind(messages[position])
            }

            else -> {
                (holder as SendMessageViewHolder).bind(messages[position])
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
    ) : RecyclerView.ViewHolder(itemView){
        lateinit var binding: ViewSendMessageItemBinding

        fun bind(message: Message){

        }
    }

    class ReceiveMessageViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView){
        lateinit var binding: ViewReceiveMessageItemBinding

        fun bind(message: Message){

        }
    }

    class SystemMessageViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView)
}
