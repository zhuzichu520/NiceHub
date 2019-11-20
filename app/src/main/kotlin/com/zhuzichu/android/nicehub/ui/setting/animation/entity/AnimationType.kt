package com.zhuzichu.android.nicehub.ui.setting.animation.entity

import androidx.navigation.AnimBuilder
import com.zhuzichu.android.nicehub.R as R2
import com.zhuzichu.android.shared.R as R1

class AnimationType {

    companion object {
        val FADE: AnimBuilder.() -> Unit = {
            enter = R2.anim.default_enter
            exit = R2.anim.default_exit
            popEnter = R2.anim.default_pop_enter
            popExit = R2.anim.default_pop_exit
        }

        val SLIDE: AnimBuilder.() -> Unit = {
            enter = R1.anim.slide_enter
            exit = R1.anim.slide_exit
            popEnter = R1.anim.slide_pop_enter
            popExit = R1.anim.slide_pop_exit
        }
    }

}