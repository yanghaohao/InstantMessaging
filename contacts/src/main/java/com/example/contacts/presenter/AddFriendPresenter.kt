package com.example.contacts.presenter

import com.example.contacts.interfaces.AddFriendContract
import com.example.data.bean.Friend

class AddFriendPresenter(
    val view: AddFriendContract.View,
) : AddFriendContract.Presenter {
    val addFriendItems = mutableListOf<Friend>()

    override fun search(key: String) {
    }
}
