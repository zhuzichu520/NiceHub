package com.zhuzichu.android.nicehub.ui.feeds.repository.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.feeds.repository.domain.UseCaseGetReadme
import com.zhuzichu.android.nicehub.ui.feeds.repository.entiy.ParamterGetReadme
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.NOT_FOUND
import com.zhuzichu.android.shared.extension.autoLoading
import javax.inject.Inject

class ViewModelRepository @Inject constructor(
    private val useCaseGetReadme: UseCaseGetReadme
) : ViewModelAnalyticsBase() {

    val bean = MutableLiveData<BeanRepository>()

    val readme = MutableLiveData<String>()

    val webviewVisibility = MutableLiveData<Int>(View.INVISIBLE)

    val maskVisibility = MutableLiveData<Int>(View.VISIBLE)

    val emptyVisibility = MutableLiveData<Int>(View.GONE)

    val showEmptyEvent = SingleLiveEvent<Unit>()

    fun loadReadme() {
        useCaseGetReadme.execute(
            ParamterGetReadme(
                bean.value?.owner?.login ?: "",
                bean.value?.name ?: ""
            )
        ).autoLoading(this)
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