package com.zhuzichu.android.shared.databinding.textview

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.zhuzichu.android.shared.extension.ParseDateFormat

@BindingAdapter(value = ["parseDataFromString"], requireAll = false)
fun parseDataFromString(textView: TextView, string: String?) {
    string?.let {
        textView.text = ParseDateFormat.getTimeAgo(it)
    }
}
