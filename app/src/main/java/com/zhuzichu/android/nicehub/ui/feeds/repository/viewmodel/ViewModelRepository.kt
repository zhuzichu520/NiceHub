package com.zhuzichu.android.nicehub.ui.feeds.repository.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.feeds.repository.domain.UseCaseGetReadme
import com.zhuzichu.android.nicehub.ui.feeds.repository.entiy.ParamterGetReadme
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.logi
import javax.inject.Inject

class ViewModelRepository @Inject constructor(
    private val useCaseGetReadme: UseCaseGetReadme
) : ViewModelAnalyticsBase() {

    val bean = MutableLiveData<BeanRepository>()

    val readme = MutableLiveData<String>()

    fun loadReadme() {
        useCaseGetReadme.execute(
            ParamterGetReadme(
                bean.value?.owner?.login ?: "",
                bean.value?.name ?: ""
            )
        ).autoLoading(this)
            .autoDispose(this)
            .subscribe({
                it.logi("ahahahah")
                readme.value = it
            }, {
                handleThrowable(it)
            })
    }
}