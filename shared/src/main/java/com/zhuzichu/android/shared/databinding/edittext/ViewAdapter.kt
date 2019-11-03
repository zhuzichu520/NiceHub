package com.zhuzichu.android.shared.databinding.edittext

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.zhuzichu.android.mvvm.databinding.BindingCommand

@BindingAdapter(value = ["onSearchCommand"], requireAll = false)
fun bindEditTextView(editText: EditText, onSearchCommand: BindingCommand<*>?) {
    editText.setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> {
                onSearchCommand?.execute()
            }
            else -> {
            }
        }
        false
    }
}

