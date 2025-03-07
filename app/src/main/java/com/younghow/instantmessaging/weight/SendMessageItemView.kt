package com.younghow.instantmessaging.weight

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.databinding.ViewSendMessageItemBinding
import java.util.Date

class SendMessageItemView(context: Context?,attributeSet: AttributeSet? = null) : RelativeLayout(context,attributeSet){

    val binding by lazy {
        ViewSendMessageItemBinding.inflate(LayoutInflater.from(context), this,true)
    }
    fun bindView(emMessage: EMMessage,showTimestamp: Boolean) {
        updateTimestamp(emMessage,showTimestamp)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when(it){
                EMMessage.Status.INPROGRESS -> {
                    binding.sendMessageProgress.visibility = View.VISIBLE
                    binding.sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = binding.sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS -> binding.sendMessageProgress.visibility = View.GONE
                EMMessage.Status.FAIL -> {
                    binding.sendMessageProgress.visibility = View.VISIBLE
                    binding.sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }

                else -> {}
            }
        }
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT)
            binding.sendMessage.text = (emMessage.body as EMTextMessageBody).message
        else
            binding.sendMessage.text = context.getString(R.string.no_text_message)
    }

    private fun updateTimestamp(emMessage: EMMessage,showTimestamp:Boolean) {
        binding.timestamp.text = com.hyphenate.util.DateUtils.getTimestampString(Date(emMessage.msgTime))
        if (showTimestamp) binding.timestamp.visibility = View.VISIBLE
        else binding.timestamp.visibility = View.GONE
    }

}