package com.younghow.instantmessaging.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.ContactListAdapter
import com.younghow.instantmessaging.contract.ContactContract
import com.younghow.instantmessaging.databinding.FragmentContactsBinding
import com.younghow.instantmessaging.extentions.startActivityEx
import com.younghow.instantmessaging.extentions.toast
import com.younghow.instantmessaging.presenter.ContactPresenter
import com.younghow.instantmessaging.ui.activity.AddFriendActivity
import com.younghow.instantmessaging.vm.ContractViewModel
import com.younghow.instantmessaging.weight.SlideBar

class ContractFragment :
    BaseFragment<FragmentContactsBinding, ContractViewModel>(),
    ContactContract.View {
    val presenter = ContactPresenter(this)

    override fun setLayout(): Int = R.layout.fragment_contacts

    override fun getViewModelClass() = ContractViewModel::class.java

    val contactListener =
        object : EMContactListener {
            override fun onContactInvited(
                p0: String?,
                p1: String?,
            ) {}

            override fun onContactDeleted(p0: String?) = presenter.loadContacts()

            override fun onFriendRequestAccepted(p0: String?) {}

            override fun onContactAdded(p0: String?) {
                presenter.loadContacts()
            }

            override fun onFriendRequestDeclined(p0: String?) {}
        }

    override fun init() {
        super.init()
        binding.header.headerTitle.text = getString(R.string.contact)
        binding.header.add.visibility = View.VISIBLE

        binding.swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            setOnRefreshListener { presenter.loadContacts() }
        }

        binding.recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }

        EMClient.getInstance().contactManager().setContactListener(contactListener)
        presenter.loadContacts()

        binding.slideBar.onSectionChange =
            object : SlideBar.onSectionChangeLisenter {
                override fun onSectionChange(firstLetter: String) {
                    binding.section.visibility = View.VISIBLE
                    binding.section.text = firstLetter
                    if (getPosition(firstLetter.toLowerCase()) != -1) {
                        binding.recyclerView.smoothScrollToPosition(getPosition(firstLetter.toLowerCase()))
                    }
                }

                override fun onSlideFinish() {
                    binding.section.visibility = View.GONE
                }
            }

        binding.header.add.setOnClickListener {
            requireContext().startActivityEx<AddFriendActivity, Pair<String, Int>>(
                AddFriendActivity::class.java,
            )
        }
    }

    private fun getPosition(firstLetter: String): Int =
        presenter.contactListItems.binarySearch { contactListItem ->
            contactListItem.firstLetter.minus(firstLetter[0])
        }

    override fun onLoadContactSuccess() {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onLoadContactFail() {
        binding.swipeRefreshLayout.isRefreshing = false
        requireContext().toast(R.string.load_contacts_failed)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().contactManager().removeContactListener(contactListener)
    }
}
