package com.zhuzichu.android.nicehub.ui.account.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.android.autoDispose
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.ActivityMain
import com.zhuzichu.android.nicehub.db.DaoUser
import com.zhuzichu.android.nicehub.db.entity.DOUser
import com.zhuzichu.android.nicehub.repository.LocalRepository
import com.zhuzichu.android.nicehub.ui.account.login.domain.UseCaseAuthorizations
import com.zhuzichu.android.nicehub.ui.account.login.domain.UseCaseGetUserByToken
import com.zhuzichu.android.nicehub.ui.profile.domain.UseCaseGetUser
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.widget.toast.toast
import okhttp3.Credentials

import javax.inject.Inject

class ViewModelLogin @Inject constructor(
    private val useCaseAuthorizations: UseCaseAuthorizations,
    private val useCaseGetUser: UseCaseGetUserByToken,
    private val globalStorage: GlobalStorage,
    private val localRepository: LocalRepository
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
                getUser(it.token ?: "")
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
                localRepository.saveUser(DOUser(it.id!!,it.login!!,it.avatarUrl,it.name))
                startActivity(ActivityMain::class.java, isPop = true)
            }, {
                handleThrowable(it)
            })
    }

}