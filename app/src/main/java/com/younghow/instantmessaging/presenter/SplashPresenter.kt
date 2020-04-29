package com.younghow.instantmessaging.presenter

import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.contract.SplashContract

class SplashPresenter(private val view: SplashContract.view) : SplashContract.Presenter {

    override fun checkLoginStatus() {
       if (isLogginedIn()) view.onLLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLogginedIn() : Boolean = EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
}