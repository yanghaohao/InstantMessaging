package com.younghow.instantmessaging.contract

interface RegisterContract {

    interface Presenter : BasePresenter{
        fun register(username:String,password:String,confirmPassword:String)
    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFail()
        fun onUserExist()
    }
}