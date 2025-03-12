package com.example.main.activity

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.commen.base.BaseActivity
import com.example.commen.extentions.startActivityEx
import com.example.main.LoginContract
import com.example.main.R
import com.example.main.databinding.ActivityLoginBinding
import com.example.main.presenter.LoginPresenter

class LoginActivity :
    BaseActivity<ActivityLoginBinding>(),
    LoginContract.view {
    private val presenter = LoginPresenter(this)

    override fun setLayout(): Int = R.layout.activity_login

    override fun onUserNameError() {
        binding.userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        binding.password.error = getString(R.string.user_name_error)
    }

    override fun onStartLogin() {
        showProgress(getString(R.string.logging))
    }

    override fun onLoginSuccess() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.login_success), Toast.LENGTH_SHORT).show()
        startActivityEx(MainActivity::class.java)
    }

    override fun onLoginFail() {
        dismissDialogProgress()
        Toast.makeText(this, this.getText(R.string.login_failed), Toast.LENGTH_SHORT).show()
    }

    override fun init() {
        super.init()
        applyWriteExternalStoragePermission()
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

    private fun applyWriteExternalStoragePermission() {
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
