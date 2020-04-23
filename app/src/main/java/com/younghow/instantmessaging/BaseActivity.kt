package com.younghow.instantmessaging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())

        init()
        setView()
    }

    open fun init() {

    }

    protected abstract fun setLayout():Int
    protected abstract fun setView()
}