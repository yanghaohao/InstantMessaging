package com.younghow.instantmessaging.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.younghow.instantmessaging.contract.ContactContract
import com.younghow.instantmessaging.data.Contact
import com.younghow.instantmessaging.data.ContactListItem
import com.younghow.instantmessaging.data.db.IMDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception

class ContactPresenter(val view:ContactContract.View) : ContactContract.Presenter {

    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            contactListItems.clear()
            IMDatabase.instance.deleteAllContact()
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                usernames.forEachIndexed { index, s ->
                    val showFirstLetter = index == 0 || s[0]!=usernames[index-1][0]
                    val contactListItem = ContactListItem(s,s[0].toLowerCase(),showFirstLetter)
                    contactListItems.add(contactListItem)

                    val contact = Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contact)
                }
                uiThread { view.onLoadContactSuccess() }
            }catch (e : HyphenateException){
                uiThread { view.onLoadContactFail() }
            }

        }

    }
}