package com.younghow.instantmessaging.presenter

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.example.main.RegisterContract
import com.example.data.bean.Mine
import com.example.commen.extentions.isValidPassword
import com.example.commen.extentions.isValidUserName
import org.jetbrains.anko.doAsync


class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun register(username: String, password: String, confirmPassword: String) {
        if (!username.isValidUserName()){
            view.onUserNameError()
            return
        }

        if (!password.isValidPassword()){
            view.onPasswordError()
            return
        }

        if (!confirmPassword.isValidPassword()){
            view.onConfirmPasswordError()
            return
        }

        registerBmob(username,password)
    }

    private fun registerBmob(username: String,password: String){
        val p2 = Mine()
        p2.userName = username
        p2.password = password
        p2.save(object : SaveListener<String>() {
            override fun done(objectId: String?, e: BmobException?) {
                if (e == null) {
                    registerEaseMob(username,password)
                } else {
                    if (e.errorCode == 202){
                        view.onUserExist()
                    }
                    view.onRegisterFail()
                }
            }
        })
    }

    private fun registerEaseMob(username: String,password: String){
        doAsync {
            try {
                EMClient.getInstance().createAccount(username,password)
                uiThread { view. onRegisterSuccess() }
            }catch (e:HyphenateException){
                uiThread { view.onRegisterFail() }
            }
        }
    }
}