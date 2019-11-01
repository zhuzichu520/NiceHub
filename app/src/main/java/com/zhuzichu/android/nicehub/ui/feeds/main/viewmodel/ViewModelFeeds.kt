package com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import javax.inject.Inject

class ViewModelFeeds @Inject constructor() : ViewModelAnalyticsBase() {
    val onClickSearch = BindingCommand<Any>({
        startFragment(R.id.action_fragmentMain_to_fragmentRepositoriesSearch)
    })
}