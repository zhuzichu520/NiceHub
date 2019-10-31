package com.zhuzichu.android.shared.databinding.widget

import android.view.View
import androidx.databinding.BindingAdapter
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.widget.toolbar.NiceToolbar

@BindingAdapter("onClickNavigation")
fun bindToolbar(toolbar: NiceToolbar, clickCommand: BindingCommand<*>?) {
    toolbar.onOnClickNavigationListener = View.OnClickListener {
        clickCommand?.execute()
    }
}