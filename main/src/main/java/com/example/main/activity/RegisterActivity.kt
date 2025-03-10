package com.example.main.activity

import android.widget.Toast
import com.example.commen.base.BaseActivity
import com.example.main.R
import com.younghow.instantmessaging.databinding.ActivityRegisterBinding
import com.younghow.instantmessaging.presenter.RegisterPresenter
import com.younghow.instantmessaging.vm.RegisterViewModel

class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterViewModel>(),
    RegisterContract.View {
    private val presenter = RegisterPresenter(this)

    override fun setLayout(): Int = R.layout.activity_register

    override fun getViewModelClass() = RegisterViewModel::class.java

    override fun onUserNameError() {
        binding.userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        binding.password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        binding.confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.register_success), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onRegisterFail() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.register_failed), Toast.LENGTH_SHORT).show()
    }

    override fun onUserExist() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.user_already_exist), Toast.LENGTH_SHORT).show()
    }

    override fun init() {
        super.init()
        binding.register.setOnClickListener { register() }
        binding.confirmPassword.setOnEditorActionListener { _, _, _ ->
            register()
            true
        }
    }

    private fun register() {
        hideSoftKeyBoard()
        val userNameString =
            binding.userName.text
                .trim()
                .toString()
        val passwordString =
            binding.password.text
                .trim()
                .toString()
        val confirmPasswordString =
            binding.confirmPassword.text
                .trim()
                .toString()

        presenter.register(userNameString, passwordString, confirmPasswordString)
    }
}
