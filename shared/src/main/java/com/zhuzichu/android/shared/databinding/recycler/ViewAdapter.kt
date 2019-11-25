package com.zhuzichu.android.shared.databinding.recycler

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.uber.autodispose.android.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.rxbinding.scrollBottom
import com.zhuzichu.android.widget.recycler.BottomRecyclerView
import java.util.concurrent.TimeUnit

@BindingAdapter(value = ["onScrollBottom"], requireAll = false)
fun bindRecyclerView(
    recyclerView: BottomRecyclerView,
    onScrollBottom: BindingCommand<*>?
) {
    recyclerView.scrollBottom()
//        .throttleFirst(1000, TimeUnit.MILLISECONDS)
        .autoDispose(recyclerView)
        .subscribe {
            onScrollBottom?.execute()
        }
}

@BindingAdapter(value = ["onRefresh"], requireAll = false)
fun bindSwipeRefreshLayout(
    swipeRefreshLayout: SwipeRefreshLayout,
    onRefresh: BindingCommand<SwipeRefreshLayout>?
) {
    swipeRefreshLayout.setOnRefreshListener {
        onRefresh?.execute(swipeRefreshLayout)
    }
}

