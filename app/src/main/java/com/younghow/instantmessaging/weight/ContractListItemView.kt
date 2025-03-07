package com.younghow.instantmessaging.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.younghow.instantmessaging.data.ContactListItem
import com.younghow.instantmessaging.databinding.ViewContactItemBinding

class ContractListItemView(
    context: Context?,
    attrs: AttributeSet? = null,
) : RelativeLayout(context, attrs) {
    private val binding by lazy {
        ViewContactItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bindView(contactListItem: ContactListItem) {
        if (contactListItem.showFirstLetter) {
            binding.firstLetter.visibility = View.VISIBLE
            binding.firstLetter.text = contactListItem.firstLetter.toString()
        } else {
            binding.firstLetter.visibility = View.GONE
        }
        binding.userName.text = contactListItem.username
    }
}
