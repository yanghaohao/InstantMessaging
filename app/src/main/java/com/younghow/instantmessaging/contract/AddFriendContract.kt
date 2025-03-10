package com.younghow.instantmessaging.contract

import com.example.commen.base.BasePresenter

interface AddFriendContract {
    interface Presenter : BasePresenter {
        fun search(key: String)
    }

    interface View {
        fun onSearchSuccess()

        fun onSearchFail()
    }
}
