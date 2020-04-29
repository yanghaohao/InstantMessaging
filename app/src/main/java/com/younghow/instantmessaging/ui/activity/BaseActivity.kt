package com.younghow.instantmessaging.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private val progressDialog by lazy {
        ProgressDialog(this)
    }

    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())

        init()
    }

    open fun init() {

    }

    fun showProgress(message : String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dissmissDialogProgress(){
        progressDialog.dismiss()
    }

    protected abstract fun setLayout():Int

    fun hideSoftKeyBoard(){
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
    }
}