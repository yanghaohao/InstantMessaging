package com.younghow.instantmessaging.contract

interface SplashContract {

    interface Presenter : BasePresenter{
        fun checkLoginStatus()
    }

    interface view{

        fun onNotLoggedIn()
        fun onLLoggedIn()
    }
}