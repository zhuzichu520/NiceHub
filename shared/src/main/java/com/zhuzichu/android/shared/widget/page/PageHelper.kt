package com.zhuzichu.android.shared.widget.page

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.BR
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import java.lang.ref.WeakReference

class PageHelper(
    private val items: AsyncDiffObservableList<Any>,
    val viewModel: ViewModelAnalyticsBase,
    private val pageSize: Int,
    private var isFirstLoad: Boolean = true,
    private val onLoadMore: ((parameter: Int) -> Unit)? = null
) {
    var page = 1
    private var isLoading = false
    private var weakRefresh: WeakReference<SwipeRefreshLayout?>? = null

    private val onClickRetry = BindingCommand<Any>({
        onScrollBottom.execute()
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

    val onScrollBottom = BindingCommand<Any>({
        if (!isFirstLoad) {
            showFinish()
            isFirstLoad = !isFirstLoad
            return@BindingCommand
        }
        if (!isLoading) {
            isLoading = true
            showLoading()
            onLoadMore?.invoke(page)
        }
    })

    val onRefresh = BindingCommand<SwipeRefreshLayout>(consumer = {
        weakRefresh = WeakReference(it)
        if (!isLoading) {
            page = 1
            onLoadMore?.invoke(page)
        } else {
            viewModel.toast("数据正在加载中")
            hideRefresh()
        }
    })


    fun addAll(list: List<Any>?) {
        hideRefresh()
        if (list.isNullOrEmpty()) {
            showEnd()
            return
        }
        if (page == 1) {
            items.update(list)
        } else {
            items.update(items.plus(list))
        }
        MainHandler.postDelayed(Runnable {
            if (list.size < pageSize) {
                showEnd()
            } else {
                showFinish()
                page = page.inc()
            }
        }, 25)
    }

    private fun hideRefresh() {
        weakRefresh?.get()?.isRefreshing = false
    }

    private fun showLoading() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_LOADING
    }

    fun showError() {
        isLoading = false
        networkViewModel.state.value = ItemViewModelNetwork.STATE_ERROR
        hideRefresh()
    }

    private fun showFinish() {
        isLoading = false
        networkViewModel.state.value = ItemViewModelNetwork.STATE_FINISH
    }

    private fun showEnd() {
        isLoading = false
        networkViewModel.state.value = ItemViewModelNetwork.STATE_END
    }
}