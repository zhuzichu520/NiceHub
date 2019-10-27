package com.zhuzichu.android.shared.extension

import timber.log.Timber

private const val TAG = "coffee"

fun Any.logi(tag: String = TAG) {
    Timber.tag(tag).i(this.toString())
}