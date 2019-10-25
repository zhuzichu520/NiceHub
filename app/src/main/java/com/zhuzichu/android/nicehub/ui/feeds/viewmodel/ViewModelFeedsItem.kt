package com.zhuzichu.android.nicehub.ui.feeds.viewmodel

import com.uber.autodispose.android.lifecycle.autoDispose
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.feeds.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.itemBindingOf
import com.zhuzichu.android.shared.extension.itemDiffOf
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import javax.inject.Inject

class ViewModelFeedsItem @Inject constructor(
    private val useCaseGetHotRepos: UseCaseGetHotRepos
) : ViewModelAnalyticsBase() {

    val items =
        AsyncDiffObservableList(itemDiffOf<ItemViewModelRepository> { oldItem, newItem -> oldItem.bean.id == newItem.bean.id })

    val itemBinding = itemBindingOf<Any>(BR.item, R.layout.item_repository)

    fun loadData(q: String) {
        useCaseGetHotRepos.execute(ParamterGetHotRepos(q, 1))
            .autoLoading(this)
            .autoDispose(lifecycleOwner)
            .subscribe({
                items.update(
                    it.items?.map { item ->
                        ItemViewModelRepository(this, item)
                    }
                )
            }, {
                handleThrowable(it)
            })
    }
}