package com.example.commen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.data.helper.RepositoryHelper

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    protected lateinit var binding: DB
    val repository = RepositoryHelper.getRepositoryHelper(requireActivity().applicationContext)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = run {
        binding = DataBindingUtil.inflate(layoutInflater, setLayout(), container, false)

        binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) = init()

    open fun init() {}

    protected abstract fun setLayout(): Int
}
