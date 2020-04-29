package com.younghow.instantmessaging.data

data class Contact(val map: MutableMap<String,Any?>){
    val _id by map
    val name by map
}