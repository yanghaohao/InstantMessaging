package com.example.contacts.presenter

import com.example.contacts.interfaces.ContactContract
import com.example.data.bean.Friend

class ContactPresenter(
    val view: ContactContract.View,
) : ContactContract.Presenter {
    val contactListItems = mutableListOf<Friend>()

    override fun loadContacts() {
    }
}
