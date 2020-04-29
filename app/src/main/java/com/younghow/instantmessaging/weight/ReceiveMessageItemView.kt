package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.younghow.instantmessaging.R
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

class ReceiveMessageItemView(context: Context?, attributeSet: AttributeSet? = null) : RelativeLayout(context,attributeSet){
    fun bindView(emMessage: EMMessage,showTimestamp: Boolean) {
        receivedMessage(emMessage)
        receivedTimestamp(emMessage,showTimestamp)
    }

    init {
        View.inflate(context, R.layout.view_receive_message_item,this)
    }

    private fun receivedMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT)
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        else
            receiveMessage.text = context.getString(R.string.no_text_message)
    }

    private fun receivedTimestamp(emMessage: EMMessage,showTimestamp: Boolean) {
        timestamp.text = com.hyphenate.util.DateUtils.getTimestampString(Date(emMessage.msgTime))
        if (showTimestamp) timestamp.visibility = View.VISIBLE
        else timestamp.visibility = View.GONE
    }
}