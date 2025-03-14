package com.example.message

import com.example.data.bean.Message
import com.example.message.interfaces.ChatContract

class ChatPresenter(
    val view: ChatContract.View,
) : ChatContract.Presenter {
    companion object {
        const val PAGE_SIZE = 10
    }

    val messages = mutableListOf<Message>()

    override fun sendMessage(
        contract: String,
        message: String,
    ) {
    }

    override fun addMessage(
        username: String,
        list: MutableList<Message>?,
    ) {
    }

    override fun loadMessage(username: String) {
    }

    override fun loadMoreMessage(username: String) {
    }
}
