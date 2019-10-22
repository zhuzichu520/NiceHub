package com.zhuzichu.android.nicehub.ui.feeds.entity

import androidx.annotation.DrawableRes
import com.zhuzichu.android.nicehub.R

enum class Language(val title: String, @DrawableRes val icon: Int) {

    KOTLIN("Kotlin", R.drawable.ic_circle_kotlin),

    JAVA("Java", R.drawable.ic_circle_java),

    OTHER("Other", R.drawable.ic_circle_other);

    companion object {

        val POPULAR_LANGUAGES = listOf(KOTLIN, JAVA)

        fun of(name: String?) = when (name) {
            KOTLIN.title -> KOTLIN
            JAVA.title -> JAVA
            OTHER.title -> OTHER
            else -> OTHER
        }
    }
}