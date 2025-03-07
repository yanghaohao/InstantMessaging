package com.younghow.instantmessaging.contract

interface AddFriendContract {
    interface Presenter : BasePresenter {
        fun search(key: String)
    }

    interface View {
        fun onSearchSuccess()

        fun onSearchFail()
    }
}
