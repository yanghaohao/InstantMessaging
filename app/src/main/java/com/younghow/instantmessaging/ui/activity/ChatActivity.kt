package com.younghow.instantmessaging.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.MessageListAdapter
import com.younghow.instantmessaging.contract.ChatContract
import com.younghow.instantmessaging.presenter.ChatPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity(),ChatContract.View {

    val presenter = ChatPresenter(this)
    lateinit var username:String
    override fun setLayout(): Int = R.layout.activity_chat
    private val messageListener = object : EMMessageListener{
        override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageChanged(p0: EMMessage?, p1: Any?) {

        }

        override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(username,p0)
            runOnUiThread { recyclerView.adapter?.notifyDataSetChanged() }
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

        send.setOnClickListener { sendMessages() }

        initRecyclerView()

        EMClient.getInstance().chatManager().addMessageListener(messageListener)

        presenter.loadMessage(username)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
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
        val message = edit.text.toString()
        presenter.sendMessage(username,message)
        edit.setOnEditorActionListener { _, _, _ ->
            sendMessages()
            true
        }
    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        username = intent.getStringExtra("username")
        headerTitle.text = username
    }

    override fun onStarSendMeesage() {
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSendMessgeSuccess() {
        recyclerView.adapter!!.notifyDataSetChanged()
        toast(R.string.send_message_success)
        edit.text.clear()
        scrollToBottom()
    }

    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size - 1)
    }

    override fun onSendMessageFail() {
        toast(R.string.send_message_failed)
        recyclerView.adapter!!.notifyDataSetChanged()
        edit.text.clear()
    }

    override fun onMessageLoaded() {
        recyclerView.adapter?.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size:Int) {
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}