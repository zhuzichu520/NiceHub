package com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.repository.PageHelper
import com.zhuzichu.android.nicehub.ui.feeds.main.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.main.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import javax.inject.Inject

class ViewModelFeedsChild @Inject constructor(
    private val useCaseGetHotRepos: UseCaseGetHotRepos
) : ViewModelAnalyticsBase() {

    companion object {
        fun <T> emptyLiveData(): LiveData<T> {
            val emptyLiveData = MutableLiveData<T>()
            emptyLiveData.value = null
            return emptyLiveData
        }
    }

    val title = MutableLiveData<String>()

    private var page = 1

    private val pageSize = 20

    private val pageHelper = PageHelper(
        AsyncDiffObservableList(itemDiffOf<ItemViewModelRepository> { oldItem, newItem -> oldItem.id == newItem.id }),
        this,
        page,
        pageSize
    ) {
        page++
        loadData()
    }

    val items = pageHelper.pageItems

    val onPageChange = pageHelper.onPageChange

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelRepository>(BR.item, R.layout.item_repository)
    }

    fun loadData() {
        useCaseGetHotRepos.execute(ParamterGetHotRepos(title.value ?: "", page, pageSize))
            .autoDispose(this)
            .subscribe({
                pageHelper.addAll(
                    it.items?.map { item ->
                        ItemViewModelRepository(this, item)
                    }
                )
            }, {
                pageHelper.showError()
                handleThrowable(it)
            })
    }

}