package com.example.message.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.commen.base.BaseActivity
import com.example.main.R
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.younghow.instantmessaging.adapter.MessageListAdapter
import com.younghow.instantmessaging.contract.ChatContract
import com.younghow.instantmessaging.databinding.ActivityChatBinding
import com.younghow.instantmessaging.presenter.ChatPresenter
import com.younghow.instantmessaging.vm.ChatViewModel

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>(),ChatContract.View {

    val presenter = ChatPresenter(this)
    lateinit var username:String
    override fun setLayout(): Int = R.layout.activity_chat
    override fun getViewModelClass() = ChatViewModel::class.java

    private val messageListener = object : EMMessageListener{
        override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageChanged(p0: EMMessage?, p1: Any?) {

        }

        override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(username,p0)
            runOnUiThread { binding.recyclerView.adapter?.notifyDataSetChanged() }
            scrollToBottom()
        }

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageRead(p0: MutableList<EMMessage>?) {
        }
    }

    override fun init() {
        super.init()
        initHeader()

        initEditText()

        binding.send.setOnClickListener { sendMessages() }

        initRecyclerView()

        EMClient.getInstance().chatManager().addMessageListener(messageListener)

        presenter.loadMessage(username)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context,presenter.messages)
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0){
                            presenter.loadMoreMessage(username)
                        }
                    }
                }
            })
        }
    }

    private fun sendMessages() {
        hideSoftKeyBoard()
        val message = binding.edit.text.toString()
        presenter.sendMessage(username,message)
        binding.edit.setOnEditorActionListener { _, _, _ ->
            sendMessages()
            true
        }
    }

    private fun initEditText() {
        binding.edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                binding.send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun initHeader() {
        binding.header.back.visibility = View.VISIBLE
        binding.header.back.setOnClickListener { finish() }

        username = intent.getStringExtra("username")
        binding.header.headerTitle.text = username
    }

    override fun onStarSendMeesage() {
        binding.recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSendMessgeSuccess() {
        binding.recyclerView.adapter!!.notifyDataSetChanged()
        Toast.makeText(this, this.getText(R.string.send_message_success), Toast.LENGTH_SHORT).show()
        binding.edit.text.clear()
        scrollToBottom()
    }

    private fun scrollToBottom() {
        binding.recyclerView.scrollToPosition(presenter.messages.size - 1)
    }

    override fun onSendMessageFail() {
        Toast.makeText(this, this.getText(R.string.send_message_failed), Toast.LENGTH_SHORT).show()
        binding.recyclerView.adapter!!.notifyDataSetChanged()
        binding.edit.text.clear()
    }

    override fun onMessageLoaded() {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size:Int) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        binding.recyclerView.scrollToPosition(size)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}