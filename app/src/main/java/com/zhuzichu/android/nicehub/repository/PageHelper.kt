package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.nicehub.*
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class PageHelper(
    private val items: AsyncDiffObservableList<Any>,
    private val viewModel: ViewModelAnalyticsBase,
    private var page: Int,
    private val pageSize: Int,
    private val pageChange: ((parameter: Int) -> Unit)? = null
) {

    private val onClickRetry = BindingCommand<Any>({
        onPageChange.execute(page)
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

    val onPageChange = BindingCommand<Int>(consumer = {
        page = it
        showLoading()
        pageChange?.invoke(page)
    })


    fun addAll(list: List<Any>?) {
        if (list.isNullOrEmpty()) {
            showEnd()
            return
        }
        items.update(items.plus(list))
        if (list.size < pageSize) {
            showEnd()
        } else {
            showFinish()
        }
    }


    private fun showLoading() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_LOADING
    }

    fun showError() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_ERROR
    }

    private fun showFinish() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_FINISH
    }

    private fun showEnd() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_END
    }
}