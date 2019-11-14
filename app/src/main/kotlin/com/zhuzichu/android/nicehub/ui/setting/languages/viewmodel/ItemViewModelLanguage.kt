package com.zhuzichu.android.nicehub.ui.setting.languages.viewmodel

import com.zhuzichu.android.mvvm.base.ItemViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand

data class ItemViewModelLanguage(
    val viewModel: ViewModelLanguages,
    val title: String,
    val locale: String,
    val isSelected: Boolean
) : ItemViewModel(viewModel) {

    val onClickItem = BindingCommand<Any>({
        viewModel.languagesChangeEvent.value = locale
    })
}