package com.example.trends.fragment

import com.example.commen.base.BaseFragment
import com.example.trends.R
import com.example.trends.databinding.FragmentDynamicBinding

class TendsFragment : BaseFragment<FragmentDynamicBinding>() {
    override fun setLayout(): Int = R.layout.fragment_dynamic

    override fun init() {
        super.init()
        val loginString = String.format(getString(R.string.logout), 1)
        binding.logout.text = loginString

        binding.logout.setOnClickListener {
        }
    }
}
