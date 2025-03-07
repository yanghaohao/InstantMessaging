package com.younghow.instantmessaging.ui.activity

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.contract.LoginContract
import com.younghow.instantmessaging.databinding.ActivityLoginBinding
import com.younghow.instantmessaging.extentions.startActivityEx
import com.younghow.instantmessaging.presenter.LoginPresenter
import com.younghow.instantmessaging.vm.LoginViewModel

class LoginActivity :
    BaseActivity<ActivityLoginBinding, LoginViewModel>(),
    LoginContract.view {
    private val presenter = LoginPresenter(this)

    override fun setLayout(): Int = R.layout.activity_login

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun onUserNameError() {
        binding.userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        binding.password.error = getString(R.string.user_name_error)
    }

    override fun onStartLogin() {
        showProgress(getString(R.string.logging))
    }

    override fun onLogedInSuccess() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.login_success), Toast.LENGTH_SHORT).show()
        startActivityEx(MainActivity::class.java)
    }

    override fun onLogedInFail() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.login_failed), Toast.LENGTH_SHORT).show()
    }

    override fun init() {
        super.init()
        applayWriteExternalStoragePermission()
        binding.newUser.setOnClickListener { startActivityEx(RegisterActivity::class.java) }
        binding.login.setOnClickListener { login() }
        binding.password.setOnEditorActionListener { _, _, _ ->
            login()
            true
        }
    }

    private fun login() {
        hideSoftKeyBoard()
        if (hasWriteExternalStoragePermission()) {
            val userNameString =
                binding.userName.text
                    .trim()
                    .toString()
            val passwordString =
                binding.password.text
                    .trim()
                    .toString()

            presenter.login(userNameString, passwordString)
        }
    }

    private fun hasWriteExternalStoragePermission(): Boolean {
        val result =
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )

        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun applayWriteExternalStoragePermission() {
        val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            login()
        } else {
            Toast
                .makeText(
                    this,
                    this.getText(R.string.permission_denied),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }
}
