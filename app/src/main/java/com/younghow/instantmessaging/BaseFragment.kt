package com.younghow.instantmessaging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(setLayout(),container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        setView()
    }

    private fun init() {

    }

    protected abstract fun setView()

    protected abstract fun setLayout():Int
}