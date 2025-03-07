package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.EMCallBackAdapter
import com.younghow.instantmessaging.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

class AddFriendListItemView(context: Context?,attributeSet: AttributeSet? = null) : RelativeLayout(context,attributeSet) {
    fun bindView(get: AddFriendItem) {
        if (get.isAdded){
            add.isEnabled = false
            add.text = context.getString(R.string.already_added)
        }else{
            add.isEnabled = true
            add.text = context.getString(R.string.add)
        }
        userName.text = get.username
        timestamp.text = get.timestamp

        add.setOnClickListener { addFriendItem(get.username) }
    }

    private fun addFriendItem(username: String) {
        EMClient.getInstance().contactManager().aysncAddContact(username,null,object : EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                context.runOnUiThread { toast(R.string.send_add_friend_failed) }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context.runOnUiThread { toast(R.string.send_add_friend_success) }
            }
        })
    }

    init {
        View.inflate(context,R.layout.view_add_friend_item,this)
    }
}