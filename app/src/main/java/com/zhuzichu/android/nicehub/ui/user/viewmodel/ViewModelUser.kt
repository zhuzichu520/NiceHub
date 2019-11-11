package com.zhuzichu.android.nicehub.ui.user.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.repository.entity.BeanUser
import com.zhuzichu.android.nicehub.ui.follower.fragment.FragmentFollower
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseFollowUser
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseGetFollowStatus
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseGetUserByLogin
import com.zhuzichu.android.nicehub.ui.user.domain.UseCaseUnFollowerUser
import com.zhuzichu.android.nicehub.ui.user.fragment.FragmentUserDirections
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelUser @Inject constructor(
    private val useCaseGetUserByLogin: UseCaseGetUserByLogin,
    private val useCaseGetFollowStatus: UseCaseGetFollowStatus,
    private val useCaseFollowUser: UseCaseFollowUser,
    private val useCaseUnFollowerUser: UseCaseUnFollowerUser
) : ViewModelAnalyticsBase() {

    val user = MutableLiveData<BeanUser>()

    val login = MutableLiveData<String>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelUserText>(BR.item, R.layout.item_user_text)
    }

    val items = MutableLiveData<List<Any>>().apply {

    }

    val onClickFollowing = BindingCommand<Any>({
        user.value?.let {
            val directions =
                FragmentUserDirections.actionFragmentUserToFragmentFollower(
                    FragmentFollower.FOLLOWER_FOLLOWING,
                    user.value?.login ?: ""
                )
            startFragment(directions)
        }
    })

    val onClickFollowers = BindingCommand<Any>({
        user.value?.let {
            val directions =
                FragmentUserDirections.actionFragmentUserToFragmentFollower(
                    FragmentFollower.FOLLOWER_FOLLOWERS,
                    user.value?.login ?: ""
                )
            startFragment(directions)
        }
    })

    fun updateUser() {
        useCaseGetUserByLogin.execute(login.value ?: "")
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                user.value = it
                toast(it.toString())
            }, {
                handleThrowable(it)
            })
    }
}