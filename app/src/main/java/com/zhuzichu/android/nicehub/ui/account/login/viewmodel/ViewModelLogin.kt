package com.zhuzichu.android.nicehub.ui.account.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.android.autoDispose
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.ui.account.login.domain.UseCaseAuthorizations
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.storage.GlobalStorage
import okhttp3.Credentials

import javax.inject.Inject

class ViewModelLogin @Inject constructor(
    private val useCaseAuthorizations: UseCaseAuthorizations,
    private val globalStorage: GlobalStorage
) : ViewModelAnalyticsBase() {

    val username = MutableLiveData("zhuzichu520")
    val password = MutableLiveData("zhuzichu123")


    val onClickLogin = BindingCommand<Any>({
        globalStorage.token = Credentials.basic(username.value ?: "", password.value ?: "")
        useCaseAuthorizations.execute(Unit)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
            }, {
                handleThrowable(it)
            })
    })

}