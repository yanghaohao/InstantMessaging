package com.example.main

import com.example.commen.interfaces.BasePresenter

interface SplashContract {
    interface Presenter : BasePresenter {
        fun checkLoginStatus()
    }

    interface view {
        fun onNotLoggedIn()

        fun onLLoggedIn()
    }
}
