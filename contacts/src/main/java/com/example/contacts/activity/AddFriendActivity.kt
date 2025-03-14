package com.example.contacts.activity

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commen.base.BaseActivity
import com.example.commen.extentions.toastForRes
import com.example.contacts.R
import com.example.contacts.adapter.AddFriendListAdapter
import com.example.contacts.databinding.ActivityAddFriendBinding
import com.example.contacts.vm.AddFriendsViewModel
import com.example.contacts.vm.AddModelFactory

class AddFriendActivity : BaseActivity<ActivityAddFriendBinding>() {
    private val addFriendsViewModel: AddFriendsViewModel by viewModels<AddFriendsViewModel> {
        AddModelFactory()
    }

    override fun setLayout(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }

        initObserver()
    }

    private fun initObserver() {
        addFriendsViewModel.search.observe(this) {
            hideSoftKeyBoard()
            showProgress(getString(R.string.searching))
        }

        addFriendsViewModel.searchSucFail.observe(this) {
            if (it) {
                binding.recyclerView.adapter!!.notifyDataSetChanged()
                this.toastForRes(R.string.search_success)
            } else {
                this.toastForRes(R.string.search_failed)
            }
            dismissDialogProgress()
        }
    }
}
