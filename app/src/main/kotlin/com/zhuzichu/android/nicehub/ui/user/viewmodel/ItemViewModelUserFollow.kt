package com.zhuzichu.android.nicehub.ui.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelUserFollow(
    viewModel: BaseViewModel
) : ItemViewModelAnalyticsBase(viewModel) {

    val state = MutableLiveData(1)

    val followState = MutableLiveData(1)

    val text = MutableLiveData<String>()

    val onClickUnfollow = BindingCommand<Any>({

    })

    val onClickFollow = BindingCommand<Any>({

    })

    fun showUnFollow() {
        state.value = 0
        followState.value = 0
    }

    fun showFollow() {
        state.value = 0
        followState.value = 1
    }

}