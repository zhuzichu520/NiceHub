package com.zhuzichu.android.nicehub.ui.feeds.viewmodel

import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelRepository(
    viewModel: BaseViewModel,
    val bean: BeanRepository
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickItem = BindingCommand<Any>({
        toast("执行了")
    })

}