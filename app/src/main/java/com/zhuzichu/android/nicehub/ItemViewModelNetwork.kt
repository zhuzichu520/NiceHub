package com.zhuzichu.android.nicehub

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelNetwork(
    viewModel: BaseViewModel,
    val onClickRetry: BindingCommand<Any>
) : ItemViewModelAnalyticsBase(viewModel) {
    companion object {
        const val STATE_LOADING = 0
        const val STATE_ERROR = 1
        const val STATE_END = 2
        const val STATE_FINISH = 3
    }

    val state = MutableLiveData(STATE_LOADING)

}