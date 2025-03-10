package com.example.message.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commen.base.BaseFragment
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.ConversationListAdapter
import com.younghow.instantmessaging.databinding.FragmentConversationBinding
import com.younghow.instantmessaging.vm.MessageViewModel

class MessageFragment : BaseFragment<FragmentConversationBinding, MessageViewModel>(){

    val conversations = mutableListOf<EMConversation>()
    val messageListLisentner = object  : EMMessageListener{
        override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
        }

        override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
            loadConversations()
        }

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageRead(p0: MutableList<EMMessage>?) {
        }
    }

    override fun setLayout(): Int = R.layout.fragment_conversation
    override fun getViewModelClass() = MessageViewModel::class.java

    override fun init() {
        super.init()
        binding.header.headerTitle.text = getString(R.string.message)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context,conversations)
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListLisentner)
    }

    private fun loadConversations() {
        doAsync {
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter?.notifyDataSetChanged() }
        }
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListLisentner)
    }
}