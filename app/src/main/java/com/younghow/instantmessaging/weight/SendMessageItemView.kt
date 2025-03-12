package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage

class SendMessageItemView(
    context: Context?,
    attributeSet: AttributeSet? = null,
) : RelativeLayout(context, attributeSet) {
    fun bindView(
        emMessage: EMMessage,
        showTimestamp: Boolean,
    ) {
        updateTimestamp(emMessage, showTimestamp)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateProgress(emMessage: EMMessage) {
    }

    private fun updateMessage(emMessage: EMMessage) {
    }

    private fun updateTimestamp(
        emMessage: EMMessage,
        showTimestamp: Boolean,
    ) {
    }
}
