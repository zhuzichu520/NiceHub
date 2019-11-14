package com.zhuzichu.android.nicehub.ui.setting.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.ui.account.ActivityAccount
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelLogout(viewModel: ViewModelSetting) : ItemViewModelAnalyticsBase(viewModel) {
    val onClickLogout = BindingCommand<Any>({
        viewModel.globalStorage.token = null
        startActivity(ActivityAccount::class.java, isPop = true)
    })
}