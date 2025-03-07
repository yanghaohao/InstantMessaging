package com.younghow.instantmessaging.data

data class ContactListItem(
    val username: String,
    val firstLetter: Char,
    val showFirstLetter: Boolean = true,
)
