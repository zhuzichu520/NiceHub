package com.zhuzichu.android.nicehub.ui.feeds.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.android.lifecycle.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.BR
import com.zhuzichu.android.nicehub.ItemViewModelLoading
import com.zhuzichu.android.nicehub.ItemViewModelNull
import com.zhuzichu.android.nicehub.R
import com.zhuzichu.android.nicehub.ui.feeds.main.domain.UseCaseGetHotRepos
import com.zhuzichu.android.nicehub.ui.feeds.main.entity.ParamterGetHotRepos
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelFeedsChild @Inject constructor(
    private val useCaseGetHotRepos: UseCaseGetHotRepos
) : ViewModelAnalyticsBase() {

    val title = MutableLiveData<String>()
    private val pageSize = 20

    private val repos =
        AsyncDiffObservableList(itemDiffOf<ItemViewModelRepository> { oldItem, newItem -> oldItem.id == newItem.id })

    val items: MergeObservableList<Any>? = MergeObservableList<Any>()
        .insertItem(ItemViewModelNull(this))
        .insertList(repos)
        .insertItem(ItemViewModelLoading(this))

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelRepository>(BR.item, R.layout.item_repository)
        map<ItemViewModelLoading>(BR.item, R.layout.item_loading)
        map<ItemViewModelNull>(BR.item, R.layout.item_null)
    }

    val onPageChange = BindingCommand<Int>(consumer = {
        loadData(it)
    })

    private fun loadData(page: Int) {
        val q = title.value ?: ""
        toast(q)
        useCaseGetHotRepos.execute(ParamterGetHotRepos(q, page, pageSize))
            .autoDispose(lifecycleOwner)
            .subscribe({
                val data = it.items
                if (!data.isNullOrEmpty()) {
                    repos.update(
                        data.map { item ->
                            ItemViewModelRepository(this, item)
                        }
                    )
                }
            }, {
                handleThrowable(it)
            })
    }
}