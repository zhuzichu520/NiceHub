package com.zhuzichu.android.mvvm.base

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.AnimBuilder
import com.zhuzichu.android.mvvm.R

internal sealed class Payload {

    data class Activity(
        var clz: Class<*>,
        var argument: BaseArgument = ArgumentDefault(),
        var isPop: Boolean = false,
        var options: Bundle = bundleOf(),
        var requestCode: Int = 0
    )

    data class Fragment(
        var actionId: Int,
        var argument: BaseArgument = ArgumentDefault(),
        var animBuilder: AnimBuilder.() -> Unit = {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    )

}