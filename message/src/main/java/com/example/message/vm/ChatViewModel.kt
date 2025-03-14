package com.example.message.vm

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.bean.Message
import com.example.data.bean.MessageType
import com.example.data.repository.MessageRepository
import com.example.message.prestener.ChatPresenter
import com.example.message.interfaces.ChatContract

class ChatViewModel(
    private val messageRepository: MessageRepository,
) : ViewModel(),
    ChatContract.View {
    var uid = 0L
    private lateinit var sendMsg: Message
    val presenter = ChatPresenter(this)
    val startSendMsg = MutableLiveData<Message>()
    val sendSuccess = MutableLiveData<Boolean>()

    fun sendEnable(editText: EditText): Boolean {
        var enable = false
        editText.doAfterTextChanged {
            enable = !it.isNullOrEmpty()
        }
        return enable
    }

    fun loadMessage(uid: Long) {
        this.uid = uid
        messageRepository.getAllMessage(uid)
    }

    fun sendMessage(msg: String?) {
        sendMsg =
            Message(
                0L,
                msg ?: "",
                uid,
                uid,
                false,
                MessageType.MINE,
                System.currentTimeMillis(),
                "",
                0,
            )
        presenter.sendMessage("", msg ?: "")
    }

    override fun onStarSendMessage() {
        startSendMsg.postValue(sendMsg)
    }

    override fun onSendMessageStatusChange(success: Boolean) {
        sendMsg.sendSuccess = if (success) 1 else -1
        sendSuccess.postValue(success)
    }
}

class ChatModelFactory(
    private val repository: MessageRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
