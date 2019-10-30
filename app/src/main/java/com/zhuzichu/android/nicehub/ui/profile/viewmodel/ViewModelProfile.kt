package com.zhuzichu.android.nicehub.ui.profile.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.ui.account.ActivityAccount
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import javax.inject.Inject

class ViewModelProfile @Inject constructor() : ViewModelAnalyticsBase() {
    val onClick = BindingCommand<Any>({
        startActivity(ActivityAccount::class.java)
    })
}