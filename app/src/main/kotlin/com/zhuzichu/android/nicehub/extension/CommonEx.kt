package com.zhuzichu.android.nicehub.extension

import androidx.navigation.AnimBuilder
import com.zhuzichu.android.shared.storage.GlobalStorage

import com.zhuzichu.android.nicehub.R as R2
import com.zhuzichu.android.shared.R as R1

fun String?.toLanguageCircleDrawable(): Int {
    return when (this) {
        "Kotlin" -> R2.drawable.ic_circle_kotlin
        "Java" -> R2.drawable.ic_circle_java
        else -> R2.drawable.ic_circle_other
    }
}

fun Int?.toAnimationBuild(): AnimBuilder.() -> Unit {
    return when (this) {
        GlobalStorage.ANIMATION_FADE ->({
            enter = R2.anim.default_enter
            exit = R2.anim.default_exit
            popEnter = R2.anim.default_pop_enter
            popExit = R2.anim.default_pop_exit
        })
        GlobalStorage.ANIMATION_SLIDE -> ({
            enter = R1.anim.slide_enter
            exit = R1.anim.slide_exit
            popEnter = R1.anim.slide_pop_enter
            popExit = R1.anim.slide_pop_exit
        })
        else -> ({
            enter = R2.anim.default_enter
            exit = R2.anim.default_exit
            popEnter = R2.anim.default_pop_enter
            popExit = R2.anim.default_pop_exit
        })
    }
}