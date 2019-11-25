package com.zhuzichu.android.nicehub.ui.repo.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.repo.search.domain.UseCaseSearchRepo
import com.zhuzichu.android.nicehub.ui.repo.search.entity.ParamterSearchRepo
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import javax.inject.Inject

class ViewModelRepoSearch @Inject constructor(
    private val useCaseSearchRepo: UseCaseSearchRepo
) : ViewModelAnalyticsBase() {

    val query = MutableLiveData<String>()

    val onSearchCommand = BindingCommand<Any>({
        showLoading()
        search(pageHelper.run {
            page = 1
            page
        })
    })

    private val pageSize = 2

    private val pageHelper = PageHelper(
        AsyncDiffObservableList(itemDiffOf<ItemViewModelRepoSearch> { oldItem, newItem -> oldItem.id == newItem.id }),
        this,
        pageSize,
        false,
        onLoadMore = {
            search(it)
        }
    )

    val items = pageHelper.pageItems

    val onScrollBottom = pageHelper.onScrollBottom

    val onRefresh = pageHelper.onRefresh

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelRepoSearch>(BR.item, R.layout.item_search_repositories)
    }


    fun search(page: Int) {
        useCaseSearchRepo.execute(
            ParamterSearchRepo(
                q = query.value.toString(),
                sort = "stars",
                order = "desc",
                page = page,
                pageSize = pageSize
            )
        ).doFinally {
            hideLoading()
        }.autoDispose(this)
            .subscribe({
                pageHelper.addAll(
                    it.items?.map { item ->
                        ItemViewModelRepoSearch(this, item)
                    }
                )
            }, {
                handleThrowable(it)
            })
    }

}