package com.zhuzichu.android.shared.databinding.view

import android.view.View
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


@BindingAdapter(value = ["onClickCommand", "isThrottleFirst"], requireAll = false)
fun onClickCommand(view: View, clickCommand: BindingCommand<*>?, isThrottleFirst: Boolean) {
    view.post {
        view.clicks().isThrottleFirst(isThrottleFirst).autoDispose(view).subscribe {
            clickCommand?.execute()
        }
    }
}


private fun <T> Observable<T>.isThrottleFirst(
    isThrottleFirst: Boolean
): Observable<T> {
    return this.compose<T> {
        if (isThrottleFirst) {
            it.throttleFirst(500L, TimeUnit.MILLISECONDS)
        }
        it
    }
}

