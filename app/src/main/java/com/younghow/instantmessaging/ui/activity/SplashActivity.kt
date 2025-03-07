package com.younghow.instantmessaging.ui.activity

import android.os.Handler
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.contract.SplashContract
import com.younghow.instantmessaging.databinding.ActivitySplashBinding
import com.younghow.instantmessaging.extentions.startActivityEx
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
