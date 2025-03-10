package com.example.commen.extentions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import java.io.Serializable

fun <T : Activity, S : Serializable> Context.startActivityEx(
    clazz: Class<T>,
    vararg pair: Pair<String, S>,
) {
    val intent = Intent(this, clazz)
    pair.forEach {
        intent.putExtra(it.first, it.second)
    }
    startActivity(intent)
}

fun Context.toast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes content: Int) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}
