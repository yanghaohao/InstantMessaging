package com.example.message.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commen.base.BaseFragment
import com.example.message.R
import com.example.message.adapter.ConversationListAdapter
import com.example.message.databinding.FragmentConversationBinding

class MessageFragment : BaseFragment<FragmentConversationBinding>() {
    override fun setLayout(): Int = R.layout.fragment_conversation

    override fun init() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context)
        }
    }
}
