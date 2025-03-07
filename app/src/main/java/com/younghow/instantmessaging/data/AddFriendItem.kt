package com.younghow.instantmessaging.data

data class AddFriendItem(
    val username: String,
    val timestamp: String,
    val isAdded: Boolean = false,
)
