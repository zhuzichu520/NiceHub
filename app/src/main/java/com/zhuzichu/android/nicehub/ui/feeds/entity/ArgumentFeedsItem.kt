package com.zhuzichu.android.nicehub.ui.feeds.entity

import com.zhuzichu.android.mvvm.base.BaseArgument
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArgumentFeedsItem(
    val title: String
) : BaseArgument()