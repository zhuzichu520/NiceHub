package com.zhuzichu.android.nicehub.ui.user.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelUserText(
    viewModel: BaseViewModel,
    @DrawableRes iconId: Int,
    text: String
) : ItemViewModelAnalyticsBase(viewModel) {

    val icon = MutableLiveData<Int>(iconId)

    val text = MutableLiveData<String>(text)
    
}