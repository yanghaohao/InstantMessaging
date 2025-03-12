package com.example.commen.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import com.example.commen.R
import com.example.commen.databinding.HeaderBinding

class ActionBar(
    context: Context,
    attributeSet: AttributeSet? = null,
) : RelativeLayout(context, attributeSet) {
    val binding by lazy {
        HeaderBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBar)
        val leftIcon = ta.getResourceId(R.styleable.ActionBar_left_icon, 0)
        val leftIconVisible = ta.getBoolean(R.styleable.ActionBar_left_icon_visible, true)
        if (leftIcon == 0) {
            binding.back.visibility = View.GONE
        } else {
            setLeftIcon(leftIcon)
        }

        if (leftIconVisible) {
            binding.back.visibility = View.VISIBLE
        } else {
            binding.back.visibility = View.GONE
        }

        val title = ta.getString(R.styleable.ActionBar_title)
        setTitle(title ?: "")

        val status = ta.getString(R.styleable.ActionBar_status)
        setStatus(status ?: "")

        val rightIcon = ta.getResourceId(R.styleable.ActionBar_right_icon, 0)

        if (rightIcon == 0) {
            binding.add.visibility = View.GONE
        } else {
            setRightIcon(rightIcon)
        }
    }

    fun setLeftIcon(
        @DrawableRes res: Int,
    ) {
        binding.back.setImageResource(res)
    }

    fun setTitle(title: String) {
        binding.headerTitle.text = title
    }

    fun setStatus(status: String) {
        binding.tvStatus.text = status
    }

    fun setRightIcon(
        @DrawableRes rightIcon: Int,
    ) {
        binding.add.setImageResource(rightIcon)
    }

    fun setBackListener(listener: OnClickListener) {
        binding.back.setOnClickListener(listener)
    }

    fun setAddListener(listener: OnClickListener) {
        binding.add.setOnClickListener(listener)
    }
}
