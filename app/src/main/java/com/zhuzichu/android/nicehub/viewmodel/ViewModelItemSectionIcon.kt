package com.zhuzichu.android.nicehub.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.base.ItemViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand

class ViewModelItemSectionIcon(
    viewModel: BaseViewModel,
    val id: Int,
    @DrawableRes val iconId: Int,
    @StringRes val textId: Int,
    private val onClickEvent: ((parameter: Int) -> Unit)? = null
) : ItemViewModel(viewModel) {

    val onClickItem = BindingCommand<Int>({
        onClickEvent?.invoke(id)
    })
}