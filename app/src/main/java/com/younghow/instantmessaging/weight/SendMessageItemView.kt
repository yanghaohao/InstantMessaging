package com.younghow.instantmessaging.weight

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.text.format.DateUtils
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.younghow.instantmessaging.R
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import kotlinx.android.synthetic.main.view_add_friend_item.view.timestamp
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.sql.Timestamp
import java.util.*

class SendMessageItemView(context: Context?,attributeSet: AttributeSet? = null) : RelativeLayout(context,attributeSet){
    fun bindView(emMessage: EMMessage,showTimestamp: Boolean) {
        updateTimestamp(emMessage,showTimestamp)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when(it){
                EMMessage.Status.INPROGRESS -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS -> sendMessageProgress.visibility = View.GONE
                EMMessage.Status.FAIL -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }
            }
        }
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT)
            sendMessage.text = (emMessage.body as EMTextMessageBody).message
        else
            sendMessage.text = context.getString(R.string.no_text_message)
    }

    private fun updateTimestamp(emMessage: EMMessage,showTimestamp:Boolean) {
        timestamp.text = com.hyphenate.util.DateUtils.getTimestampString(Date(emMessage.msgTime))
        if (showTimestamp) timestamp.visibility = View.VISIBLE
        else timestamp.visibility = View.GONE
    }

    init {
        View.inflate(context, R.layout.view_send_message_item,this)
    }
}