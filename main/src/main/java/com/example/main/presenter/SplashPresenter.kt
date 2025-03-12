package com.example.main.presenter

import com.example.main.SplashContract

class SplashPresenter(
    private val view: SplashContract.view,
) : SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLogin()) view.onLLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLogin(): Boolean = false
}
