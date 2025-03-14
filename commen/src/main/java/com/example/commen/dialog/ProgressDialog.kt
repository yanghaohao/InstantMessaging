package com.example.commen.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.example.commen.databinding.DialogProgressBinding

class ProgressDialog(
    val context: Context,
) : Dialog(context) {
    private lateinit var dialogProgressBinding: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogProgressBinding = DialogProgressBinding.inflate(LayoutInflater.from(context))
    }

    fun setMessage(msg: String) {
        dialogProgressBinding.tvMsg.text = msg
    }
}
