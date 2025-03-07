package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.databinding.ViewConversationItemBinding
import java.util.Date

class ConversationListItemView(
    context: Context?,
    attributeSet: AttributeSet? = null,
) : RelativeLayout(context, attributeSet) {
    private val binding by lazy {
        ViewConversationItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bindView(emConversation: EMConversation) {
        binding.userName.text = emConversation.conversationId()
        if (emConversation.lastMessage.type == EMMessage.Type.TXT) {
            val body = emConversation.lastMessage.body as EMTextMessageBody
            binding.lastMessage.text = body.message
        } else {
            binding.lastMessage.text = context.getString(R.string.no_text_message)
        }

        val timeStampStr = DateUtils.getTimestampString(Date(emConversation.lastMessage.msgTime))
        binding.timestamp.text = timeStampStr

        if (emConversation.unreadMsgCount > 0) {
            binding.unreadCount.visibility = View.VISIBLE
            binding.unreadCount.text = emConversation.unreadMsgCount.toString()
        } else {
            binding.unreadCount.visibility = View.GONE
        }
    }
}
