package com.zhuzichu.android.shared.databinding.recycler

import androidx.databinding.BindingAdapter
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.widget.recycler.OnScrollBottomListener
import com.zhuzichu.android.widget.recycler.PaginationRecyclerView

@BindingAdapter(value = ["onLoadMoreCommand"], requireAll = false)
fun bindPageChangeCommand(
    recyclerView: PaginationRecyclerView,
    onLoadMoreCommand: BindingCommand<*>?
) {
    recyclerView.setOnPageChangeListener(object : OnScrollBottomListener {
        override fun onScrollBottom() {
            onLoadMoreCommand?.execute()
        }
    })
}