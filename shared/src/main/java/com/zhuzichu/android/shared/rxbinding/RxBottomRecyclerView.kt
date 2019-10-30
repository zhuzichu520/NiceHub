@file:JvmName("RxBottomRecyclerView")
@file:JvmMultifileClass

package com.zhuzichu.android.shared.rxbinding

import com.jakewharton.rxbinding3.internal.checkMainThread
import com.zhuzichu.android.widget.recycler.BottomRecyclerView
import com.zhuzichu.android.widget.recycler.OnScrollBottomListener
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

fun BottomRecyclerView.scrollBottom(): Observable<Unit> {
    return ScrollBottomObservable(this)
}

private class ScrollBottomObservable(
    private val recyclerView: BottomRecyclerView
) : Observable<Unit>() {

    override fun subscribeActual(observer: Observer<in Unit>) {

        if (!checkMainThread(observer)) {
            return
        }

        val listener = Listener(recyclerView, observer)
        observer.onSubscribe(listener)
        recyclerView.setOnScrollBottomListener(listener)
    }

}


private class Listener(
    private val recyclerView: BottomRecyclerView,
    private val observer: Observer<in Unit>
) : MainThreadDisposable(), OnScrollBottomListener {

    override fun onScrollBottom() {
        if (!isDisposed) {
            observer.onNext(Unit)
        }
    }

    override fun onDispose() {
        recyclerView.setOnScrollBottomListener(null)
    }
}