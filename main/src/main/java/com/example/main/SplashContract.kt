package com.example.main

import com.example.commen.base.BasePresenter

interface SplashContract {
    interface Presenter : BasePresenter {
        fun checkLoginStatus()
    }

    interface view {
        fun onNotLoggedIn()

        fun onLLoggedIn()
    }
}
