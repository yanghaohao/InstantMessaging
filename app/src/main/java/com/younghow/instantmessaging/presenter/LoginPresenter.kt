package com.younghow.instantmessaging.presenter

import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.adapter.EMCallBackAdapter
import com.example.main.LoginContract
import com.example.commen.extentions.isValidPassword
import com.example.commen.extentions.isValidUserName

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
        EMClient.getInstance().login(
            username,
            password,
            object : EMCallBackAdapter() {
                override fun onSuccess() {
                    super.onSuccess()
                    EMClient.getInstance().groupManager().loadAllGroups()
                    EMClient.getInstance().chatManager().loadAllConversations()

                    uiThread { view.onLogedInSuccess() }
                }

                override fun onError(
                    p0: Int,
                    p1: String?,
                ) {
                    super.onError(p0, p1)
                    uiThread { view.onLogedInFail() }
                }
            },
        )
    }
}
