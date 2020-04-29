package com.younghow.instantmessaging.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.ContactListAdapter
import com.younghow.instantmessaging.contract.ContactContract
import com.younghow.instantmessaging.fragment.BaseFragment
import com.younghow.instantmessaging.presenter.ContactPresenter
import com.younghow.instantmessaging.ui.activity.AddFriendActivity
import com.younghow.instantmessaging.weight.SlideBar
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity

class ContractFragment : BaseFragment() , ContactContract.View{

    val presenter = ContactPresenter(this)
    override fun setLayout(): Int = R.layout.fragment_contacts
    val contactListener = object : EMContactListener{
        override fun onContactInvited(p0: String?, p1: String?) {}

        override fun onContactDeleted(p0: String?) = presenter.loadContacts()


        override fun onFriendRequestAccepted(p0: String?) {}

        override fun onContactAdded(p0: String?) {
            presenter.loadContacts()
        }

        override fun onFriendRequestDeclined(p0: String?) {}
    }

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            setOnRefreshListener { presenter.loadContacts() }
        }

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context,presenter.contactListItems)
        }

        EMClient.getInstance().contactManager().setContactListener(contactListener)
        presenter.loadContacts()

        slideBar.onSectionChange = object : SlideBar.onSectionChangeLisenter{
            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                if (getPosition(firstLetter.toLowerCase())!=-1){
                    recyclerView.smoothScrollToPosition(getPosition(firstLetter.toLowerCase()))
                }
            }

            override fun onSlideFinish() {
                section.visibility = View.GONE
            }
        }

        add.setOnClickListener{ context!!.startActivity<AddFriendActivity>() }
    }

    private fun getPosition(firstLetter: String): Int =
        presenter.contactListItems.binarySearch {
            contactListItem -> contactListItem.firstLetter.minus(firstLetter[0])
        }


    override fun onLoadContactSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onLoadContactFail() {
        swipeRefreshLayout.isRefreshing = false
        context!!.toast(R.string.load_contacts_failed)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().contactManager().removeContactListener(contactListener)
    }
}