package com.example.commen.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.commen.R
import com.example.commen.view.ActionBar
import com.example.data.helper.RepositoryHelper

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    private val progressDialog by lazy {
        ProgressDialog(this)
    }
    val repository = RepositoryHelper.getRepositoryHelper(this.applicationContext)

    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    protected lateinit var binding: DB
    protected var backFuc: (()->Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, setLayout())
        findViewById<ActionBar>(R.id.action_bar)?.setOnClickListener {
            backFuc?.invoke()
            finish()
        }

        init()
    }

    open fun init() {
    }

    fun showProgress(message: String) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissDialogProgress() {
        progressDialog.dismiss()
    }

    protected abstract fun setLayout(): Int

    fun hideSoftKeyBoard() {
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}
