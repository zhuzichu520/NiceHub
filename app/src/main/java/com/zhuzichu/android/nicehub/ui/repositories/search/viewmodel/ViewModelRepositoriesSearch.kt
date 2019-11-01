package com.zhuzichu.android.nicehub.ui.repositories.search.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import javax.inject.Inject

class ViewModelRepositoriesSearch @Inject constructor() : ViewModelAnalyticsBase() {
    val onSearchSubmit = BindingCommand<String>(consumer = {
        toast(it)
    })
}