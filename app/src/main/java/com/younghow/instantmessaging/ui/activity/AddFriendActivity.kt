package com.younghow.instantmessaging.ui.activity

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commen.base.BaseActivity
import com.younghow.instantmessaging.R
import com.example.contacts.adapter.AddFriendListAdapter
import com.younghow.instantmessaging.contract.AddFriendContract
import com.younghow.instantmessaging.databinding.ActivityAddFriendBinding
import com.younghow.instantmessaging.presenter.AddFriendPresenter
import com.younghow.instantmessaging.vm.AddFriendsViewModel

class AddFriendActivity :
    BaseActivity<ActivityAddFriendBinding, AddFriendsViewModel>(),
    AddFriendContract.View {
    val presenter = AddFriendPresenter(this)

    override fun setLayout(): Int = R.layout.activity_add_friend

    override fun getViewModelClass() = AddFriendsViewModel::class.java

    override fun init() {
        super.init()

        binding.header.headerTitle.text = getString(R.string.add_friend)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context, presenter.addFriendItems)
        }

        binding.search.setOnClickListener { search() }
        binding.userName.setOnEditorActionListener { _, _, _ ->
            search()
            true
        }
    }

    private fun search() {
        hideSoftKeyBoard()
        showProgress(getString(R.string.searching))
        val username = binding.userName.text.toString()
        presenter.search(username)
    }

    override fun onSearchSuccess() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.search_success), Toast.LENGTH_SHORT).show()
        binding.recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSearchFail() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.search_failed), Toast.LENGTH_SHORT).show()
    }
}
