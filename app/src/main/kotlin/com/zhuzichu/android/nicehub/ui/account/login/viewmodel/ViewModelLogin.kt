package com.zhuzichu.android.nicehub.ui.account.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.ActivityMain
import com.zhuzichu.android.nicehub.db.entity.DOUser
import com.zhuzichu.android.nicehub.manager.UserManager
import com.zhuzichu.android.nicehub.repository.LocalRepository
import com.zhuzichu.android.nicehub.ui.account.login.domain.UseCaseAuthorizations
import com.zhuzichu.android.nicehub.ui.account.login.domain.UseCaseGetUserByToken
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.storage.GlobalStorage
import okhttp3.Credentials
import javax.inject.Inject

class ViewModelLogin @Inject constructor(
    private val useCaseAuthorizations: UseCaseAuthorizations,
    private val useCaseGetUser: UseCaseGetUserByToken,
    private val globalStorage: GlobalStorage,
    private val localRepository: LocalRepository,
    private val userManager: UserManager
) : ViewModelAnalyticsBase() {

    val username = MutableLiveData("zhuzichu520")
    val password = MutableLiveData("zhuzichu123")

    val onClickLogin = BindingCommand<Any>({
        authorizations()
    })

    private fun authorizations() {
        val basicToken = Credentials.basic(username.value ?: "", password.value ?: "")
        useCaseAuthorizations.execute(basicToken)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                getUser("token ${it.token}")
            }, {
                handleThrowable(it)
            })
    }

    private fun getUser(token: String) {
            useCaseGetUser.execute(token)
                .autoLoading(this)
                .autoDispose(this)
                .subscribe({
                    globalStorage.token = token
                    userManager.user.value=it
                    localRepository.saveUser(DOUser(it.id!!, it.login!!, it.avatarUrl, it.name))
                    startActivity(ActivityMain::class.java, isPop = true)
                }, {
                    handleThrowable(it)
                })
    }

}