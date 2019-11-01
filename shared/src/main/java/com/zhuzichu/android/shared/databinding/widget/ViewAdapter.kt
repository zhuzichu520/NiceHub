package com.zhuzichu.android.shared.databinding.widget

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.widget.toolbar.NiceToolbar

@BindingAdapter("onClickNavigation")
fun bindToolbar(toolbar: NiceToolbar, clickCommand: BindingCommand<*>?) {
    toolbar.onOnClickNavigationListener = View.OnClickListener {
        clickCommand?.execute()
    }
}

@BindingAdapter(value = ["onSearchChange", "onSearchSubmit"], requireAll = false)
fun bindSearchView(
    searchView: SearchView,
    onSearchChange: BindingCommand<*>?,
    onSearchSubmit: BindingCommand<*>?
) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            onSearchSubmit?.execute(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            onSearchChange?.execute(newText)
            return true
        }
    })
}