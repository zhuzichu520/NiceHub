package com.zhuzichu.android.nicehub.ui.account.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.android.autoDispose
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.ActivityMain
import com.zhuzichu.android.nicehub.ui.account.login.domain.UseCaseAuthorizations
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.widget.toast.toast
import okhttp3.Credentials

import javax.inject.Inject

class ViewModelLogin @Inject constructor(
    private val useCaseAuthorizations: UseCaseAuthorizations,
    private val globalStorage: GlobalStorage
) : ViewModelAnalyticsBase() {

    val username = MutableLiveData("zhuzichu520")
    val password = MutableLiveData("zhuzichu123")

    val onClickLogin = BindingCommand<Any>({
        val basicToken = Credentials.basic(username.value ?: "", password.value ?: "")
        useCaseAuthorizations.execute(basicToken)
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                globalStorage.token=it.token
                startActivity(ActivityMain::class.java, isPop = true)
            }, {
                handleThrowable(it)
            })
    })

}