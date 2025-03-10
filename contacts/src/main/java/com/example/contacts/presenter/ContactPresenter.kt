package com.example.contacts.presenter

import com.example.contacts.interfaces.ContactContract
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.younghow.instantmessaging.data.Contact
import com.younghow.instantmessaging.data.ContactListItem
import org.jetbrains.anko.doAsync

class ContactPresenter(val view: ContactContract.View) : ContactContract.Presenter {

    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            contactListItems.clear()
            com.example.data.db.UserDao.instance.deleteAllContact()
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                usernames.forEachIndexed { index, s ->
                    val showFirstLetter = index == 0 || s[0]!=usernames[index-1][0]
                    val contactListItem = ContactListItem(s,s[0].toLowerCase(),showFirstLetter)
                    contactListItems.add(contactListItem)

                    val contact = Contact(mutableMapOf("name" to s))
                    com.example.data.db.UserDao.instance.saveContact(contact)
                }
                uiThread { view.onLoadContactSuccess() }
            }catch (e : HyphenateException){
                uiThread { view.onLoadContactFail() }
            }

        }

    }
}