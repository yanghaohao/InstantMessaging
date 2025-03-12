package com.example.main.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.commen.base.BaseActivity
import com.example.contacts.fragment.ContractFragment
import com.example.main.R
import com.example.main.databinding.ActivityMainBinding
import com.example.message.fragment.MessageFragment
import com.example.trends.fragment.TendsFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val fragments = arrayOf(MessageFragment(), ContractFragment(), TendsFragment())

    override fun setLayout(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        binding.bottomBar.setOnNavigationItemSelectedListener { item ->
            binding.actionBar.setTitle(
                when (item.itemId) {
                    R.id.nav_message -> {
                        binding.fragmentFrame.currentItem = 0
                        getString(R.string.message)
                    }

                    R.id.nav_contract -> {
                        binding.fragmentFrame.currentItem = 1
                        getString(R.string.contact)
                    }

                    R.id.nav_trends -> {
                        binding.fragmentFrame.currentItem = 2
                        getString(R.string.dynamic)
                    }

                    else -> {
                        binding.fragmentFrame.currentItem = 3
                        getString(R.string.mine)
                    }
                },
            )
            false
        }

        binding.fragmentFrame.adapter =
            object :
                FragmentPagerAdapter(
                    supportFragmentManager,
                    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                ) {
                override fun getItem(position: Int): Fragment = fragments[position]

                override fun getCount(): Int = fragments.size
            }

        binding.fragmentFrame.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {
                }

                override fun onPageSelected(position: Int) {
                    binding.actionBar.setTitle(
                        when (position) {
                            0 -> {
                                binding.bottomBar.selectedItemId = R.id.nav_message
                                getString(R.string.message)
                            }
                            1 -> {
                                binding.bottomBar.selectedItemId = R.id.nav_contract
                                getString(R.string.contact)
                            }
                            2 -> {
                                binding.bottomBar.selectedItemId = R.id.nav_trends
                                getString(R.string.dynamic)
                            }
                            else -> {
                                binding.bottomBar.selectedItemId = R.id.nav_mine
                                getString(R.string.mine)
                            }
                        }
                    )
                }
            },
        )
    }
}
