package com.example.contacts.interfaces

import com.example.commen.interfaces.BasePresenter

interface AddFriendContract {
    interface Presenter : BasePresenter {
        fun search(key: String)
    }

    interface View {
        fun onSearchStateChange(state:Boolean)
    }
}
