package com.zhuzichu.android.nicehub.ui.follower.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.repository.entity.BeanFollower
import com.zhuzichu.android.nicehub.ui.follower.fragment.FragmentFollowerDirections
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelFollower(
    viewModel: BaseViewModel,
    bean: BeanFollower
) : ItemViewModelAnalyticsBase(viewModel) {

    val id = bean.id

    val avatar = MutableLiveData(bean.avatarUrl)

    val login = MutableLiveData(bean.login)

    val onClickItem = BindingCommand<Any>({
        val directions =
            FragmentFollowerDirections.actionFragmentFollowerToFragmentUser(login.value ?: "")
        startFragment(directions)
    })
}