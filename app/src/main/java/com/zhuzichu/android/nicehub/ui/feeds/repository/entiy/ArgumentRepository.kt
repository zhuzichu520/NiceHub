package com.zhuzichu.android.nicehub.ui.feeds.repository.entiy

import com.zhuzichu.android.mvvm.base.BaseArgument
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArgumentRepository(
    val bean: BeanRepository?
) : BaseArgument()