package com.zhuzichu.android.nicehub.ui.profile.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.manager.UserManager
import com.zhuzichu.android.nicehub.ui.account.ActivityAccount
import com.zhuzichu.android.nicehub.ui.account.login.domain.UseCaseGetUser
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.storage.GlobalStorage
import javax.inject.Inject

class ViewModelProfile @Inject constructor(
    private val useCaseGetUser: UseCaseGetUser,
    private val userManager: UserManager,
    private val globalStorage: GlobalStorage
) : ViewModelAnalyticsBase() {

    val user = userManager.user

    val onClickSetting = BindingCommand<Any>({
        startFragment(R.id.action_fragmentMain_to_fragmentSetting)
    })

    val onClickLogout = BindingCommand<Any>({
        globalStorage.token = null
        startActivity(ActivityAccount::class.java, isPop = true)
    })

    fun updateUser() {
        useCaseGetUser.execute(Unit)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                userManager.user.value = it
            }, {
                handleThrowable(it)
            })
    }

}