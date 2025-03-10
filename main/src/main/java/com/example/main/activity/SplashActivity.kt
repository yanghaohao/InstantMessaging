package com.example.main.activity

import android.os.Handler
import com.example.commen.base.BaseActivity
import com.younghow.instantmessaging.databinding.ActivitySplashBinding
import com.example.commen.extentions.startActivityEx
import com.example.main.R
import com.younghow.instantmessaging.presenter.SplashPresenter
import com.younghow.instantmessaging.vm.SplashViewModel

class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    SplashContract.view {
    private val presenter = SplashPresenter(this)

    companion object {
        const val DELAY = 2000L
    }

    private val handler by lazy {
        Handler()
    }

    override fun setLayout(): Int = R.layout.activity_splash

    override fun getViewModelClass() = SplashViewModel::class.java

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    override fun onNotLoggedIn() {
        handler.postDelayed(
            {
                startActivityEx(LoginActivity::class.java)
                finish()
            },
            DELAY,
        )
    }

    override fun onLLoggedIn() = startActivityEx(MainActivity::class.java)
}
