package com.younghow.instantmessaging.contract

import com.hyphenate.chat.EMMessage

interface ChatContract {

    interface Presenter : BasePresenter{
        fun sendMessage(contract: String,message:String)
        fun addMessage(username:String,list:MutableList<EMMessage>?)
        fun loadMessage(username: String)
        fun loadMoreMessage(username: String)
    }

    interface View{
        fun onStarSendMeesage()
        fun onSendMessgeSuccess()
        fun onSendMessageFail()
        fun onMessageLoaded()
        fun onMoreMessageLoaded(size:Int)
    }
}