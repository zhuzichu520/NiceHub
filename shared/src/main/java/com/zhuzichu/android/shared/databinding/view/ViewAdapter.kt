package com.zhuzichu.android.shared.databinding.view

import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEachIndexed
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding3.view.clicks
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


@BindingAdapter(value = ["onClickCommand", "isThrottleFirst"], requireAll = false)
fun onClickCommand(view: View, clickCommand: BindingCommand<*>?, isThrottleFirst: Boolean?) {
    view.post {
        view.clicks().isThrottleFirst(isThrottleFirst ?: true).subscribe {
            clickCommand?.execute()
        }
    }
}

private fun <T> Observable<T>.isThrottleFirst(
    isThrottleFirst: Boolean
): Observable<T> {
    return this.compose<T> {
        if (isThrottleFirst) {
            it.throttleFirst(150L, TimeUnit.MILLISECONDS)
        } else {
            it
        }
    }
}

@BindingAdapter(value = ["displayedChild"], requireAll = false)
fun bindViewGroup(viewGroup: ViewGroup, position: Int?) {
    viewGroup.forEachIndexed { index, view ->
        if (position == index) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}

@BindingAdapter(value = ["visibility"], requireAll = false)
fun bindViewVisibility(view: View, visibility: Int) {
    view.visibility = visibility
}


