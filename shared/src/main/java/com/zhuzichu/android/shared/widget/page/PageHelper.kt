package com.zhuzichu.android.shared.widget.page

import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.BR
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class PageHelper(
    private val items: AsyncDiffObservableList<Any>,
    val viewModel: ViewModelAnalyticsBase,
    private val pageSize: Int,
    private val loadMore: ((parameter: Int) -> Unit)? = null
) {
    private var page = 1
    private var isLoading = false

    private val onClickRetry = BindingCommand<Any>({
        onLoadMore.execute()
    })

    private val networkViewModel = ItemViewModelNetwork(viewModel, onClickRetry)

    val pageItems: MergeObservableList<Any>? = MergeObservableList<Any>()
        .insertItem(ItemViewModelNull(viewModel))
        .insertList(items)
        .insertItem(networkViewModel)

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelNetwork>(BR.item, R.layout.item_network)
        map<ItemViewModelNull>(BR.item, R.layout.item_null)
    }

    val onLoadMore = BindingCommand<Any>({
        if (!isLoading) {
            isLoading = true
            showLoading()
            loadMore?.invoke(page)
        }
    })


    fun addAll(list: List<Any>?) {
        if (list.isNullOrEmpty()) {
            showEnd()
            return
        }
        items.update(items.plus(list))
        MainHandler.postDelayed(Runnable {
            if (list.size < pageSize) {
                showEnd()
            } else {
                showFinish()
                page = page.inc()
                isLoading = false
            }
        }, 25)
    }


    private fun showLoading() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_LOADING
    }

    fun showError() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_ERROR
        isLoading = false
    }

    private fun showFinish() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_FINISH
    }

    private fun showEnd() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_END
    }
}