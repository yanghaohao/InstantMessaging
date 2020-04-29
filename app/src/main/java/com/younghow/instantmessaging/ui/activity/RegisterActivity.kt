package com.younghow.instantmessaging.ui.activity

import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.contract.RegisterContract
import com.younghow.instantmessaging.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(),RegisterContract.View {

    private val presenter = RegisterPresenter(this)

    override fun setLayout(): Int =
        R.layout.activity_register
    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dissmissDialogProgress()
        toast(R.string.register_success)
        finish()
    }

    override fun onRegisterFail() {
        dissmissDialogProgress()
        toast(R.string.register_failed)
    }

    override fun onUserExist() {
        dissmissDialogProgress()
        toast(R.string.user_already_exist)
    }

    override fun init() {
        super.init()
        register.setOnClickListener{ register() }
        confirmPassword.setOnEditorActionListener { _, _, _ ->
            register()
            true
        }
    }

    private fun register(){

        hideSoftKeyBoard()
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        val confirmPasswordString = confirmPassword.text.trim().toString()

        presenter.register(userNameString,passwordString,confirmPasswordString)
    }
}