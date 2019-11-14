package com.zhuzichu.android.nicehub.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.manager.UserManager
import com.zhuzichu.android.nicehub.ui.follower.fragment.FragmentFollower
import com.zhuzichu.android.nicehub.ui.main.fragment.FragmentMainDirections
import com.zhuzichu.android.nicehub.ui.profile.domain.UseCaseGetContributions
import com.zhuzichu.android.nicehub.ui.profile.domain.UseCaseGetUser
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import javax.inject.Inject

class ViewModelProfile @Inject constructor(
    private val useCaseGetUser: UseCaseGetUser,
    private val useCaseGetContributions: UseCaseGetContributions,
    private val userManager: UserManager
) : ViewModelAnalyticsBase() {

    val user = userManager.user

    val contributions = MutableLiveData<String>()

    val onClickSetting = BindingCommand<Any>({
        startFragment(R.id.action_fragmentMain_to_fragmentSetting)
    })

    val onClickFollowing = BindingCommand<Any>({
        userManager.doLogin {
            val directions =
                FragmentMainDirections.actionFragmentMainToFragmentFollower(
                    FragmentFollower.FOLLOWER_FOLLOWING,
                    user.value?.login ?: ""
                )
            startFragment(directions)
        }
    })

    val onClickFollowers = BindingCommand<Any>({
        userManager.doLogin {
            val directions =
                FragmentMainDirections.actionFragmentMainToFragmentFollower(
                    FragmentFollower.FOLLOWER_FOLLOWERS,
                    user.value?.login ?: ""
                )
            startFragment(directions)
        }
    })

    fun updateUser() {
        useCaseGetUser.execute(Unit)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                userManager.user.value = it
                updateContributions()
            }, {
                handleThrowable(it)
            })
    }

    private fun updateContributions() {
        useCaseGetContributions.execute(user.value?.login ?: "")
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                contributions.value = it
            }, {
                handleThrowable(it)
            })
    }

}