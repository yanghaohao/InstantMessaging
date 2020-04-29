package com.younghow.instantmessaging.contract

interface LoginContract {

    interface Presenter : BasePresenter{
        fun login(username:String,password:String)
    }

    interface view {
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLogedInSuccess()
        fun onLogedInFail()
    }
}