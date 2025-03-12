package com.example.contacts.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commen.base.BaseFragment
import com.example.commen.extentions.toast
import com.example.commen.view.SlideBar
import com.example.contacts.R
import com.example.contacts.adapter.ContactListAdapter
import com.example.contacts.databinding.FragmentContactsBinding
import com.example.contacts.interfaces.ContactContract
import com.example.contacts.presenter.ContactPresenter

class ContractFragment :
    BaseFragment<FragmentContactsBinding>(),
    ContactContract.View {
    val presenter = ContactPresenter(this)

    override fun setLayout(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
//        binding.header.headerTitle.text = getString(R.string.contact)
//        binding.header.add.visibility = View.VISIBLE

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

//        binding.header.add.setOnClickListener {
//            requireContext().startActivityEx<AddFriendActivity, Pair<String, Int>>(
//                AddFriendActivity::class.java,
//            )
//        }
    }

    private fun getPosition(firstLetter: String): Int =
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
