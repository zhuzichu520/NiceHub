package com.zhuzichu.android.shared.fountain.rx.common

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

internal fun Executor.toScheduler() = Schedulers.from(this)

internal fun Scheduler.toExecutor() = Executor {
  Completable.fromAction { it.run() }
      .subscribeOn(this)
      .subscribe()
}
