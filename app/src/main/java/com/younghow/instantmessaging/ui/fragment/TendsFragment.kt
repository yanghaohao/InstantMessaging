package com.younghow.instantmessaging.ui.fragment

import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.adapter.EMCallBackAdapter
import com.younghow.instantmessaging.fragment.BaseFragment
import com.younghow.instantmessaging.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class TendsFragment : BaseFragment() {
    override fun setLayout(): Int = R.layout.fragment_dynamic

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)

        val loginString = String.format(getString(R.string.logout),EMClient.getInstance().currentUser)
        logout.text = loginString

        logout.setOnClickListener { logout() }
    }

    private fun logout(){
        EMClient.getInstance().logout(true, object : EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                context!!.runOnUiThread {
                    toast(R.string.logout_success)
                    context!!.startActivity<LoginActivity>()
                    activity!!.finish()
                }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context!!.runOnUiThread { toast(R.string.login_failed) }
            }
        })
    }
}