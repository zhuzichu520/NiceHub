package com.zhuzichu.android.shared.extension

import com.zhuzichu.android.mvvm.base.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

fun <T> schedulersTransformer(): FlowableTransformer<T, T> {
    return FlowableTransformer { observable ->
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> exceptionTransformer(): FlowableTransformer<T, T> {
    return FlowableTransformer { observable ->
        observable.onErrorResumeNext(HttpResponseFunc())
    }
}

private class HttpResponseFunc<T> : Function<Throwable, Flowable<T>> {
    override fun apply(t: Throwable): Flowable<T> {
        return Flowable.error(t.handleException())
    }
}

fun <T> Flowable<T>.autoLoading(viewModel: BaseViewModel): Flowable<T> =
    this.compose<T> {
        it.doOnSubscribe { viewModel.showLoading() }
            .doFinally { viewModel.hideLoading() }
    }

fun <T> Flowable<T>.bindToException(): Flowable<T> =
    this.compose<T> {
        it.onErrorResumeNext(HttpResponseFunc())
    }

fun <T> Flowable<T>.bindToSchedulers(): Flowable<T> =
    this.compose<T> {
        it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }