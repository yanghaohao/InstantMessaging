package com.example.main

import com.example.commen.interfaces.BasePresenter

interface RegisterContract {
    interface Presenter : BasePresenter {
        fun register(
            username: String,
            password: String,
            confirmPassword: String,
        )
    }

    interface View {
        fun onUserNameError()

        fun onPasswordError()

        fun onConfirmPasswordError()

        fun onStartRegister()

        fun onRegisterSuccess()

        fun onRegisterFail()

        fun onUserExist()
    }
}
