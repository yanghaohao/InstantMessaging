package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.databinding.ViewReceiveMessageItemBinding
import java.util.Date

class ReceiveMessageItemView(
    context: Context?,
    attributeSet: AttributeSet? = null,
) : RelativeLayout(context, attributeSet) {
    val binding by lazy {
        ViewReceiveMessageItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bindView(
        emMessage: EMMessage,
        showTimestamp: Boolean,
    ) {
        receivedMessage(emMessage)
        receivedTimestamp(emMessage, showTimestamp)
    }

    private fun receivedMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            binding.receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            binding.receiveMessage.text = context.getString(R.string.no_text_message)
        }
    }

    private fun receivedTimestamp(
        emMessage: EMMessage,
        showTimestamp: Boolean,
    ) {
        binding.timestamp.text =
            com.hyphenate.util.DateUtils
                .getTimestampString(Date(emMessage.msgTime))
        if (showTimestamp) {
            binding.timestamp.visibility = View.VISIBLE
        } else {
            binding.timestamp.visibility = View.GONE
        }
    }
}
