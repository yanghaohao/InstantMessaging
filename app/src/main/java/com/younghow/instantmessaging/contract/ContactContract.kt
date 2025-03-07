package com.younghow.instantmessaging.contract

interface ContactContract {
    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun onLoadContactSuccess()

        fun onLoadContactFail()
    }
}
