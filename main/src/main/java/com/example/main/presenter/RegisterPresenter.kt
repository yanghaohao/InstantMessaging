package com.example.main.presenter

import com.example.commen.extentions.isValidPassword
import com.example.commen.extentions.isValidUserName
import com.example.main.RegisterContract

class RegisterPresenter(
    val view: RegisterContract.View,
) : RegisterContract.Presenter {
    override fun register(
        username: String,
        password: String,
        confirmPassword: String,
    ) {
        if (!username.isValidUserName()) {
            view.onUserNameError()
            return
        }

        if (!password.isValidPassword()) {
            view.onPasswordError()
            return
        }

        if (!confirmPassword.isValidPassword()) {
            view.onConfirmPasswordError()
            return
        }

        registerBmob(username, password)
    }

    private fun registerBmob(
        username: String,
        password: String,
    ) {
    }

    private fun registerEaseMob(
        username: String,
        password: String,
    ) {
    }
}
