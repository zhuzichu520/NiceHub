package com.zhuzichu.android.nicehub.ui.user.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelUserFollow(
    val viewModel: BaseViewModel,
    val onClickUnfollow:BindingCommand<Any>,
    val onClickFollow:BindingCommand<Any>
) : ItemViewModelAnalyticsBase(viewModel) {

    val display = MutableLiveData(1)

    val visibility = MutableLiveData(View.INVISIBLE)

    val text = MutableLiveData<String>()

    fun showUnFollow() {
        visibility.value = View.VISIBLE
        display.value = 0
    }

    fun showFollow() {
        visibility.value = View.VISIBLE
        display.value = 1
    }

}