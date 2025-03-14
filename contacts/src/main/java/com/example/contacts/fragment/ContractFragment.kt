package com.example.contacts.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commen.base.BaseFragment
import com.example.commen.extentions.toast
import com.example.commen.view.SlideBar
import com.example.contacts.R
import com.example.contacts.adapter.ContactListAdapter
import com.example.contacts.databinding.FragmentContactsBinding
import com.example.contacts.interfaces.ContactContract
import com.example.contacts.presenter.ContactPresenter
import com.example.contacts.vm.ContractModelFactory
import com.example.contacts.vm.ContractViewModel
import com.example.data.helper.RepositoryHelper

class ContractFragment :
    BaseFragment<FragmentContactsBinding>(),
    ContactContract.View {
    val presenter = ContactPresenter(this)
    val viewModel: ContractViewModel by viewModels<ContractViewModel> {
        ContractModelFactory(RepositoryHelper.getRepositoryHelper(requireContext()).friendsRepository)
    }

    override fun setLayout(): Int = R.layout.fragment_contacts

    override fun init() {
        binding.swipeRefreshLayout.apply {
            setColorSchemeResources(com.example.commen.R.color.qq_blue)
            setOnRefreshListener { presenter.loadContacts() }
        }

        binding.recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }

        presenter.loadContacts()
        viewModel.getAllFriends(0L,0L)
        viewModel.allFriend.observe(this) {
        }

        binding.slideBar.onSectionChange =
            object : SlideBar.onSectionChangeLisenter {
                override fun onSectionChange(firstLetter: String) {
                    binding.section.visibility = View.VISIBLE
                    binding.section.text = firstLetter
                    if (getPosition() != -1) {
                        binding.recyclerView.smoothScrollToPosition(
                            getPosition(),
                        )
                    }
                }

                override fun onSlideFinish() {
                    binding.section.visibility = View.GONE
                }
            }
    }

    private fun getPosition(): Int =
        presenter.contactListItems.binarySearch { contactListItem ->
            1
        }

    override fun onLoadContactSuccess() {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onLoadContactFail() {
        binding.swipeRefreshLayout.isRefreshing = false
        requireContext().toast(R.string.load_contacts_failed)
    }
}
