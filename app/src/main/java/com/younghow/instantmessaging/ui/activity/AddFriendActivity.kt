package com.younghow.instantmessaging.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.AddFriendListAdapter
import com.younghow.instantmessaging.contract.AddFriendContract
import com.younghow.instantmessaging.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(),AddFriendContract.View{

    val presenter = AddFriendPresenter(this)

    override fun setLayout(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context,presenter.addFriendItems)
        }

        search.setOnClickListener { search() }
        userName.setOnEditorActionListener { _, _, _ ->
            search()
            true
        }
    }

    private fun search(){
        hideSoftKeyBoard()
        showProgress(getString(R.string.searching))
        val username = userName.text.toString()
        presenter.search(username)
    }

    override fun onSearchSuccess() {
        dissmissDialogProgress()
        toast(R.string.search_success)
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSearchFail() {
        dissmissDialogProgress()
        toast(R.string.search_failed)
    }
}