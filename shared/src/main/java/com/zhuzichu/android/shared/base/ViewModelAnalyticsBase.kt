package com.zhuzichu.android.shared.base

import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.extension.ResponseThrowable

open class ViewModelAnalyticsBase : BaseViewModel() {
    fun handleThrowable(throwable: Throwable) {
        when (throwable) {
            is ResponseThrowable -> toast(throwable.message)
        }
        throwable.printStackTrace()
    }
}