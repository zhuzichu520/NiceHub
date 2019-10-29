package com.zhuzichu.android.shared.base

import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.extension.ResponseThrowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase.ViewModelEvent.CREATED
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase.ViewModelEvent.CLEARED

open class ViewModelAnalyticsBase : BaseViewModel(),
    LifecycleScopeProvider<ViewModelAnalyticsBase.ViewModelEvent> {

    companion object {

        private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewModelEvent> { event ->
            when (event) {
                CREATED -> CLEARED
                else -> throw LifecycleEndedException(
                    "Cannot bind to ViewModel lifecycle after onCleared."
                )
            }
        }
    }

    private val lifecycleEvents = BehaviorSubject.createDefault(CREATED)

    override fun lifecycle(): Observable<ViewModelEvent> {
        return lifecycleEvents.hide()
    }

    override fun correspondingEvents(): CorrespondingEventsFunction<ViewModelEvent> {
        return CORRESPONDING_EVENTS
    }

    override fun peekLifecycle(): ViewModelEvent? {
        return lifecycleEvents.value
    }

    enum class ViewModelEvent {
        CREATED, CLEARED
    }


    override fun onCleared() {
        lifecycleEvents.onNext(CLEARED)
        super.onCleared()
    }


    fun handleThrowable(throwable: Throwable) {
        when (throwable) {
            is ResponseThrowable -> toast(throwable.message)
        }
        throwable.printStackTrace()
    }


}