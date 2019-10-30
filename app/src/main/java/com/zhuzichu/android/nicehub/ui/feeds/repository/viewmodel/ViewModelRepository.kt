package com.zhuzichu.android.nicehub.ui.feeds.repository.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.ui.feeds.main.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.main.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import javax.inject.Inject

class ViewModelRepository @Inject constructor(
    private val useCaseGetHotRepos: UseCaseGetHotRepos
) : ViewModelAnalyticsBase() {

    val onClick = BindingCommand<Any>({
        loadData()
    })


    private fun loadData() {
        useCaseGetHotRepos.execute(ParamterGetHotRepos("Kotlin", 1, 20))
            .autoLoading(this)
            .autoDispose(this)
            .subscribe({

            }, {
                handleThrowable(it)
            })
    }
}