package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage

class ReceiveMessageItemView(
    context: Context?,
    attributeSet: AttributeSet? = null,
) : RelativeLayout(context, attributeSet) {
    fun bindView(
        emMessage: EMMessage,
        showTimestamp: Boolean,
    ) {
        receivedMessage(emMessage)
        receivedTimestamp(emMessage, showTimestamp)
    }

    private fun receivedMessage(emMessage: EMMessage) {
    }

    private fun receivedTimestamp(
        emMessage: EMMessage,
        showTimestamp: Boolean,
    ) {
    }
}
