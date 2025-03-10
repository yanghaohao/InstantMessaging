package com.example.main.activity

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.commen.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.younghow.instantmessaging.R
import com.younghow.instantmessaging.databinding.ActivityMainBinding
import com.example.commen.extentions.startActivityEx
import com.example.contacts.fragment.ContractFragment
import com.example.message.fragment.MessageFragment
import com.example.trends.fragment.TendsFragment
import com.younghow.instantmessaging.vm.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val fragments = arrayOf(MessageFragment(), ContractFragment(), TendsFragment())

    private val messageLisenter = object : EMMessageListener{
        override fun onMessageRecalled(p0: MutableList<EMMessage>?) {

        }

        override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
        }

        override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
        }

        override fun onMessageRead(p0: MutableList<EMMessage>?) {
        }
    }
    override fun setLayout(): Int = R.layout.activity_main
    override fun getViewModelClass() = MainViewModel::class.java

    override fun init() {
        super.init()
        binding.bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_message -> fragment_frame.currentItem = 0
                R.id.nav_contract -> fragment_frame.currentItem = 1
                R.id.nav_trends -> fragment_frame.currentItem = 2
            }
            false
        }

        fragment_frame.adapter = object : FragmentPagerAdapter(supportFragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
            override fun getItem(position: Int): Fragment = fragments[position]
            override fun getCount(): Int = fragments.size
        }

        fragment_frame.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.bottomBar.selectedItemId = R.id.nav_message
                    1 -> binding.bottomBar.selectedItemId = R.id.nav_contract
                    2 -> binding.bottomBar.selectedItemId = R.id.nav_trends
                }
            }
        })

        EMClient.getInstance().chatManager().addMessageListener(messageLisenter)
        EMClient.getInstance().addConnectionListener(object : EMConnectionListener{
            override fun onConnected() {

            }

            override fun onDisconnected(p0: Int) {
                if (p0 == EMError.USER_LOGIN_ANOTHER_DEVICE){
                    startActivityEx(LoginActivity::class.java)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        val menuView = binding.bottomBar.getChildAt(0) as BottomNavigationMenuView
        val tab = menuView.getChildAt(0)
        val itemView = tab as BottomNavigationItemView
        val badge = LayoutInflater.from(this).inflate(R.layout.im_badge, menuView, false)
        itemView.addView(badge)
        val textView = badge.findViewById<TextView>(R.id.tv)
        textView.text = "1"
        textView.visibility = View.VISIBLE
    }
}
