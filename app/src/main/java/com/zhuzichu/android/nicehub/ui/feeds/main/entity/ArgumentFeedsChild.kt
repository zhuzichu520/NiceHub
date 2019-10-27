package com.zhuzichu.android.nicehub.ui.feeds.main.entity

import com.zhuzichu.android.mvvm.base.BaseArgument
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArgumentFeedsChild(
    val title: String
) : BaseArgument()