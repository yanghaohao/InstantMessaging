package com.younghow.instantmessaging.ui.fragment

import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.EMCallBackAdapter
import com.younghow.instantmessaging.databinding.FragmentDynamicBinding
import com.younghow.instantmessaging.extentions.startActivityEx
import com.younghow.instantmessaging.extentions.toast
import com.younghow.instantmessaging.ui.activity.LoginActivity
import com.younghow.instantmessaging.vm.TendsViewModel

class TendsFragment : BaseFragment<FragmentDynamicBinding,TendsViewModel>() {
    override fun setLayout(): Int = R.layout.fragment_dynamic
    override fun getViewModelClass() = TendsViewModel::class.java

    override fun init() {
        super.init()
        binding.header.headerTitle.text = getString(R.string.dynamic)

        val loginString = String.format(getString(R.string.logout),EMClient.getInstance().currentUser)
        binding.logout.text = loginString

        binding.logout.setOnClickListener { logout() }
    }

    private fun logout(){
        EMClient.getInstance().logout(true, object : EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                requireContext().runOnUiThread {
                    requireContext().toast(R.string.logout_success)
                    requireContext().startActivityEx<LoginActivity,Int>(LoginActivity::class.java)
                    requireActivity().finish()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                requireContext().runOnUiThread {
                    requireContext().toast(R.string.login_failed)
                }
            }
        })
    }
}