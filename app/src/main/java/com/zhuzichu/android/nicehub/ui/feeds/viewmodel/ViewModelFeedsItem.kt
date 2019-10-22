package com.zhuzichu.android.nicehub.ui.feeds.viewmodel

import com.uber.autodispose.android.lifecycle.autoDispose
import com.zhuzichu.android.nicehub.ui.feeds.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import javax.inject.Inject

class ViewModelFeedsItem @Inject constructor(
    private val useCaseGetHotRepos: UseCaseGetHotRepos
) : ViewModelAnalyticsBase() {

    fun loadData(q: String) {
        useCaseGetHotRepos.execute(ParamterGetHotRepos(q,1))
            .autoLoading(this)
            .autoDispose(lifecycleOwner)
            .subscribe({

            }, {
                handleThrowable(it)
            })
    }
}