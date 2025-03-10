package com.example.commen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: DB
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = run {
        binding = DataBindingUtil.inflate(layoutInflater, setLayout(), container, false)
        viewModel = ViewModelProvider(this)[getViewModelClass()]

        binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) = init()

    open fun init() {}

    protected abstract fun setLayout(): Int

    protected abstract fun getViewModelClass(): Class<VM>
}
