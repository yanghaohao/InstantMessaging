package com.example.main.presenter

import com.example.commen.extentions.isValidPassword
import com.example.commen.extentions.isValidUserName
import com.example.main.LoginContract

class LoginPresenter(
    val view: LoginContract.view,
) : LoginContract.Presenter {
    override fun login(
        username: String,
        password: String,
    ) {
        if (username.isValidUserName()) {
            if (password.isValidPassword()) {
                view.onStartLogin()
                loginEaseMob(username, password)
            } else {
                view.onPasswordError()
            }
        } else {
            view.onUserNameError()
        }
    }

    private fun loginEaseMob(
        username: String,
        password: String,
    ) {

    }
}
