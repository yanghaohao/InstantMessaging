package com.example.contacts.activity

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commen.base.BaseActivity
import com.example.contacts.R
import com.example.contacts.adapter.AddFriendListAdapter
import com.example.contacts.databinding.ActivityAddFriendBinding
import com.example.contacts.interfaces.AddFriendContract
import com.example.contacts.presenter.AddFriendPresenter

class AddFriendActivity :
    BaseActivity<ActivityAddFriendBinding>(),
    AddFriendContract.View {
    val presenter = AddFriendPresenter(this)

    override fun setLayout(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
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
