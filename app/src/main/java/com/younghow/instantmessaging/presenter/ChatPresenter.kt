package com.younghow.instantmessaging.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.younghow.instantmessaging.adapter.EMCallBackAdapter
import com.younghow.instantmessaging.contract.ChatContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {

    companion object{
        const val PAGE_SIZE = 10
    }

    val messages = mutableListOf<EMMessage>()

    override fun sendMessage(contract: String, message: String) {
        val emMessage = EMMessage.createTxtSendMessage(message,contract)
        emMessage.setMessageStatusCallback(object : EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                uiThread { view.onSendMessgeSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                uiThread { view.onSendMessageFail() }
            }
        })

        messages.add(emMessage)
        view.onStarSendMeesage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addMessage(username: String, list: MutableList<EMMessage>?) {
        list?.let { messages.addAll(it) }
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }

    override fun loadMessage(username: String) {
        doAsync {
            val coversation = EMClient.getInstance().chatManager().getConversation(username)
            coversation.markAllMessagesAsRead()
            messages.addAll(coversation.allMessages)
            uiThread { view.onMessageLoaded() }
        }
    }

    override fun loadMoreMessage(username: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            val startMsgId = messages[0].msgId
            val loadMoreMsgFromDB = conversation.loadMoreMsgFromDB(startMsgId, PAGE_SIZE)
            messages.addAll(0,loadMoreMsgFromDB)
            uiThread { view.onMoreMessageLoaded(loadMoreMsgFromDB.size) }
        }
    }
}