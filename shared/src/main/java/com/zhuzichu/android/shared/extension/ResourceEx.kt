package com.zhuzichu.android.shared.extension

import androidx.core.content.ContextCompat
import com.zhuzichu.android.shared.global.AppGlobal.context

fun Int.toStringByResId(): String {
    return context.getString(this)
}

fun Int.toColorByResId(): Int {
    return ContextCompat.getColor(context, this)
}

fun Int.toArrayByResId(): Array<String> {
    return context.resources.getStringArray(this)
}