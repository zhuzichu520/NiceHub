package com.zhuzichu.android.nicehub.ui.repositories.detail.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.repositories.detail.domain.UseCaseGetReadme
import com.zhuzichu.android.nicehub.ui.repositories.detail.entiy.ParamterGetReadme
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.NOT_FOUND
import com.zhuzichu.android.shared.extension.autoLoading
import javax.inject.Inject

class ViewModelRepository @Inject constructor(
    private val useCaseGetReadme: UseCaseGetReadme
) : ViewModelAnalyticsBase() {

    val readme = MutableLiveData<String>()

    val emptyVisibility = MutableLiveData(View.GONE)

    val showEmptyEvent = SingleLiveEvent<Unit>()

    fun loadReadme(login: String, name: String) {
        useCaseGetReadme.execute(ParamterGetReadme(login, name))
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({
                readme.value = it
            }, {
                handleThrowable(it) {
                    if (this.code == NOT_FOUND) {
                        showEmptyEvent.value = Unit
                    }
                }
            })
    }
}