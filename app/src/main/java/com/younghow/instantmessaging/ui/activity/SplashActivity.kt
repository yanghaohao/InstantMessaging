package com.younghow.instantmessaging.ui.activity

import android.os.Handler
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.contract.SplashContract
import com.younghow.instantmessaging.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(),SplashContract.view{

    private val presenter = SplashPresenter(this)

    companion object{
        const val DELAY = 2000L
    }

    private val handler by lazy {
        Handler()
    }

    override fun setLayout(): Int =
        R.layout.activity_splash

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        },
            DELAY
        )
    }

    override fun onLLoggedIn() = startActivity<MainActivity>()
}