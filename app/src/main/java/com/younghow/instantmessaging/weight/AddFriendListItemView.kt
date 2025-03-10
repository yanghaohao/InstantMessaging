package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.EMCallBackAdapter
import com.younghow.instantmessaging.data.AddFriendItem
import com.younghow.instantmessaging.databinding.ViewAddFriendItemBinding
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

class AddFriendListItemView(context: Context?,attributeSet: AttributeSet? = null) : RelativeLayout(context,attributeSet) {

    val binding by lazy {
        ViewAddFriendItemBinding.inflate(LayoutInflater.from(context),this,true)
    }
    fun bindView(get: AddFriendItem) {
        if (get.isAdded){
            binding.add.isEnabled = false
            binding.add.text = context.getString(R.string.already_added)
        }else{
            binding.add.isEnabled = true
            binding.add.text = context.getString(R.string.add)
        }
        binding.userName.text = get.username
        binding.timestamp.text = get.timestamp

        binding.add.setOnClickListener { addFriendItem(get.username) }
    }

    private fun addFriendItem(username: String) {
        EMClient.getInstance().contactManager().aysncAddContact(username,null,object : EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                context.runOnUiThread { context.toast(R.string.send_add_friend_failed) }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context.runOnUiThread { context.toast(R.string.send_add_friend_success) }
            }
        })
    }

}