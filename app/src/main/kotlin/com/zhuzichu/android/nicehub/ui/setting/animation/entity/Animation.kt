package com.zhuzichu.android.nicehub.ui.setting.animation.entity

import com.zhuzichu.android.nicehub.R as R2
import com.zhuzichu.android.shared.R as R1

enum class Animation(enter: Int, exit: Int, popEnter: Int, popExit: Int) {

    Fade(
        R2.anim.default_enter,
        R2.anim.default_exit,
        R2.anim.default_pop_enter,
        R2.anim.default_pop_exit
    ),
    Slide(
        R1.anim.slide_enter,
        R1.anim.slide_exit,
        R1.anim.slide_pop_enter,
        R1.anim.slide_pop_exit
    )
}