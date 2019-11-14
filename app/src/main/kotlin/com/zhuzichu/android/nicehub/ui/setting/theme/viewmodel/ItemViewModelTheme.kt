package com.zhuzichu.android.nicehub.ui.setting.theme.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

data class ItemViewModelTheme(
    val viewModel: ViewModelTheme,
    val titleId: Int,
    val mode: Int,
    val isSelected: Boolean
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickItem = BindingCommand<Any>({
        viewModel.themeChangeEvent.value = mode
    })
}