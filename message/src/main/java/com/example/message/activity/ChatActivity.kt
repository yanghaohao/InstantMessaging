package com.example.message.activity

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.commen.base.BaseActivity
import com.example.commen.extentions.toastForRes
import com.example.data.helper.RepositoryHelper
import com.example.message.R
import com.example.message.adapter.MessageListAdapter
import com.example.message.databinding.ActivityChatBinding
import com.example.message.vm.ChatModelFactory
import com.example.message.vm.ChatViewModel

class ChatActivity : BaseActivity<ActivityChatBinding>() {
    private val chatViewModel by viewModels<ChatViewModel> {
        ChatModelFactory(RepositoryHelper.getRepositoryHelper(this).messageRepository)
    }
    val messageListAdapter = MessageListAdapter(this)

    override fun setLayout(): Int = R.layout.activity_chat

    override fun init() {
        super.init()

        binding.vm = chatViewModel
        initRecyclerView()
        initObserver()
    }

    fun initObserver() {
        chatViewModel.startSendMsg.observe(this) {
            messageListAdapter.addMsg(it)
            scrollToBottom()
        }

        chatViewModel.sendSuccess.observe(this) {
            if (it) {
                this.toastForRes(R.string.send_message_success)
            } else {
                this.toastForRes(R.string.send_message_failed)
            }
            binding.edit.text.clear()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
            adapter = MessageListAdapter(context)
            addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(
                        recyclerView: RecyclerView,
                        newState: Int,
                    ) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            val linearLayoutManager = layoutManager as LinearLayoutManager
                            if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                                chatViewModel.loadMessage(0L)
                            }
                        }
                    }
                },
            )
        }
    }

    private fun scrollToBottom() {
        binding.recyclerView.scrollToPosition(messageListAdapter.itemCount - 1)
    }
}
