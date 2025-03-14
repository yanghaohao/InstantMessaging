package com.example.contacts.adapter

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter

@BindingAdapter("editSearch")
fun editSearch(
    editText: EditText,
    func: () -> Unit,
) {
    editText.doAfterTextChanged {
        func.invoke()
    }
}
