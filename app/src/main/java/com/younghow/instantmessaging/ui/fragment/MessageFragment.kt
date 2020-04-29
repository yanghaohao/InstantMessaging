package com.younghow.instantmessaging.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.ConversationListAdapter
import com.younghow.instantmessaging.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MessageFragment : BaseFragment(){

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

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.message)

        recyclerView.apply {
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