package com.younghow.instantmessaging.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils
import com.younghow.instantmessaging.weight.ReceiveMessageItemView
import com.younghow.instantmessaging.weight.SendMessageItemView

class MessageListAdapter(
    val context: Context,
    val messages: List<EMMessage>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_TYPE_SEND_MESSAGE = 0
        const val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        if (messages[position].direct() == EMMessage.Direct.SEND) {
            return ITEM_TYPE_SEND_MESSAGE
        } else {
            return ITEM_TYPE_RECEIVE_MESSAGE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_SEND_MESSAGE) {
            SendMessageViewHolder(
                SendMessageItemView(
                    context,
                ),
            )
        } else {
            ReceiveMessageViewHolder(ReceiveMessageItemView(context))
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val showTimeStamp = isShowTimeStamp(position)
        if (getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE) {
            val sendMessageItemView = holder.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[position], showTimeStamp)
        } else {
            val receiveMessageItemView = holder.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages[position], showTimeStamp)
        }
    }

    private fun isShowTimeStamp(position: Int): Boolean {
        var showTimeStamp = true
        if (position > 0) {
            showTimeStamp =
                !DateUtils.isCloseEnough(messages[position].msgTime, messages[position - 1].msgTime)
        }
        return showTimeStamp
    }

    class SendMessageViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView)

    class ReceiveMessageViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView)
}
