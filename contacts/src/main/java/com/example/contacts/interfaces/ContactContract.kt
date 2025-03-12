package com.example.contacts.interfaces

import com.example.commen.interfaces.BasePresenter

interface ContactContract {
    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun onLoadContactSuccess()

        fun onLoadContactFail()
    }
}
