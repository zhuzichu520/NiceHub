package com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.feeds.main.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.main.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.page.PageHelper
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


    private val pageSize = 20

    private val pageHelper = PageHelper(
        AsyncDiffObservableList(itemDiffOf<ItemViewModelRepository> { oldItem, newItem -> oldItem.id == newItem.id }),
        this,
        pageSize
    ) {
        loadData(it)
    }

    val items = pageHelper.pageItems

    val onLoadMore = pageHelper.onLoadMore

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelRepository>(BR.item, R.layout.item_repository)
    }

    private fun loadData(page: Int) {
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