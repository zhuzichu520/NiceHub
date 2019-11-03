package com.zhuzichu.android.nicehub.extension

import com.zhuzichu.android.nicehub.R

fun String?.toLanguageCircleDrawable(): Int {
    return when (this) {
        "Kotlin" -> R.drawable.ic_circle_kotlin
        "Java" -> R.drawable.ic_circle_java
        else -> R.drawable.ic_circle_other
    }
}