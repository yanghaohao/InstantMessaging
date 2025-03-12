package com.example.message

import com.example.commen.interfaces.BasePresenter
import com.example.data.bean.Message

interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMessage(
            contract: String,
            message: String,
        )

        fun addMessage(
            username: String,
            list: MutableList<Message>?,
        )

        fun loadMessage(username: String)

        fun loadMoreMessage(username: String)
    }

    interface View {
        fun onStarSendMeesage()

        fun onSendMessgeSuccess()

        fun onSendMessageFail()

        fun onMessageLoaded()

        fun onMoreMessageLoaded(size: Int)
    }
}
