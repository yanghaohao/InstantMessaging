package com.younghow.instantmessaging.ui.activity

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.contract.LoginContract
import com.younghow.instantmessaging.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(),LoginContract.view {

    private val presenter = LoginPresenter(this)

    override fun setLayout(): Int =
        R.layout.activity_login

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.user_name_error)
    }

    override fun onStartLogin() {
        showProgress(getString(R.string.logging))
    }

    override fun onLogedInSuccess() {
        dissmissDialogProgress()
        toast("登陆成功")
        startActivity<MainActivity>()
    }

    override fun onLogedInFail() {
        dissmissDialogProgress()
        toast(getString(R.string.login_failed))
    }

    override fun init() {
        super.init()
        applayWriteExternalStoragePermission()
        newUser.setOnClickListener { startActivity<RegisterActivity>() }
        login.setOnClickListener{login()}
        password.setOnEditorActionListener { _, _, _ ->
            login()
            true
        }
    }

    private fun login(){
        hideSoftKeyBoard()
        if (hasWriteExternalStoragePermission()){
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()

            presenter.login(userNameString,passwordString)
        }
    }

    private fun hasWriteExternalStoragePermission():Boolean{
        val result = ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        return  result == PackageManager.PERMISSION_GRANTED
    }

    private fun applayWriteExternalStoragePermission(){
        val permissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permissions,0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) login() else toast(R.string.permission_denied)
    }
}