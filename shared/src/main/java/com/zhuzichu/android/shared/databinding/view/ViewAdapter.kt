package com.zhuzichu.android.shared.databinding.view

import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.core.view.forEachIndexed
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding3.view.clicks
import com.zhuzichu.android.libs.tool.setVisibility
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


@BindingAdapter(value = ["onClickCommand", "isThrottleFirst"], requireAll = false)
fun onClickCommand(view: View, clickCommand: BindingCommand<*>?, isThrottleFirst: Boolean) {
    view.post {
        view.clicks().isThrottleFirst(isThrottleFirst).subscribe {
            clickCommand?.execute()
        }
    }
}


private fun <T> Observable<T>.isThrottleFirst(
    isThrottleFirst: Boolean
): Observable<T> {
    return this.compose<T> {
        if (isThrottleFirst) {
            it.throttleFirst(300L, TimeUnit.MILLISECONDS)
        }
        it
    }
}

@BindingAdapter(value = ["displayedChild"], requireAll = false)
fun onViewFlipper(viewGroup: ViewGroup, position: Int?) {
    viewGroup.forEachIndexed { index, view ->
        if (position == index) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}



