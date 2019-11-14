package com.zhuzichu.android.nicehub.ui.setting.main.viewmodel

import androidx.annotation.StringRes
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelSetting(
    viewModel: BaseViewModel,
    val id: Int,
    @StringRes val textId: Int
) : ItemViewModelAnalyticsBase(viewModel) {

    companion object {
        const val LANGUAGES = 0x00
        const val THEME = 0x01
    }

    val onClickItem = BindingCommand<Any>({
        when (id) {
            LANGUAGES -> startFragment(R.id.action_fragmentSetting_to_fragmentLanguages)
            THEME -> startFragment(R.id.action_fragmentSetting_to_fragmentTheme)
            else -> {
            }
        }
    })

}