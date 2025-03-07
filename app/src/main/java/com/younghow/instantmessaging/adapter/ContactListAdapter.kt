package com.younghow.instantmessaging.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.data.ContactListItem
import com.younghow.instantmessaging.extentions.startActivityEx
import com.younghow.instantmessaging.extentions.toast
import com.younghow.instantmessaging.ui.activity.ChatActivity
import com.younghow.instantmessaging.weight.ContractListItemView

class ContactListAdapter(
    private val context: Context,
    val contactListItems: MutableList<ContactListItem>,
) : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {
    class ContactListViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ContactListViewHolder = ContactListViewHolder(ContractListItemView(context))

    override fun getItemCount(): Int = contactListItems.size

    override fun onBindViewHolder(
        holder: ContactListViewHolder,
        position: Int,
    ) {
        val contactListView = holder.itemView as ContractListItemView
        contactListView.bindView(contactListItems[position])
        val userName = contactListItems.get(position).username
        contactListView.setOnClickListener {
            context.startActivityEx(
                ChatActivity::class.java,
                Pair("userName", userName),
            )
        }
        contactListView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_friend_message), userName)
            AlertDialog
                .Builder(context)
                .setTitle(R.string.delete_friend_title)
                .setMessage(message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.confirm) { _, _ ->
                    deleteFriend(userName)
                }.show()
            true
        }
    }

    private fun deleteFriend(userName: String) {
        EMClient
            .getInstance()
            .contactManager()
            .aysncDeleteContact(
                userName,
                object : EMCallBackAdapter() {
                    override fun onSuccess() {
                        super.onSuccess()
                        context.runOnUiThread { context.toast(context.getString(R.string.delete_friend_success)) }
                    }

                    override fun onError(
                        p0: Int,
                        p1: String?,
                    ) {
                        super.onError(p0, p1)
                        context.runOnUiThread { context.toast(context.getString(R.string.delete_friend_failed)) }
                    }
                },
            )
    }
}
