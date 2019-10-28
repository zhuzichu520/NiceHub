package com.zhuzichu.android.shared.databinding.recycler

import androidx.databinding.BindingAdapter
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.widget.recycler.OnPageChangeListener
import com.zhuzichu.android.widget.recycler.PaginationRecyclerView

@BindingAdapter(value = ["onPageChangeCommand"], requireAll = false)
fun bindPageChangeCommand(
    recyclerView: PaginationRecyclerView,
    onPageChangeCommand: BindingCommand<*>?
) {
    recyclerView.setOnPageChangeListener(object : OnPageChangeListener {
        override fun onPageChange(page: Int) {
            onPageChangeCommand?.execute(page)
        }
    })
}
