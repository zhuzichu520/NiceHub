package com.zhuzichu.android.libs.internal

import android.os.Handler
import android.os.Looper

object MainHandler {

    private val mHandler by lazy { Handler(Looper.getMainLooper()) }

    /**
     * 在主线程执行runnable
     *
     * @param runnable 主线程待执行的任务
     */
    fun post(runnable: Runnable) {
        if (Looper.getMainLooper().thread === Thread.currentThread()) {
            // 当前线程已经在主线程，立即执行
            runnable.run()
        } else {
            // 当前线程已经在后台线程，切换到主线程后执行
            mHandler.post(runnable)
        }
    }

    /**
     * 延迟delayMillis毫秒后，在主线程执行runnable
     *
     * @param runnable    主线程待执行的任务
     * @param delayMillis 延时X毫秒后执行任务
     */
    fun postDelayed(runnable: Runnable, delayMillis: Long) {
        mHandler.postDelayed(runnable, delayMillis)
    }

    /**
     * 删除待执行的runnable
     *
     * @param runnable 待执行任务
     */
    fun removeCallbacks(runnable: Runnable) {
        mHandler.removeCallbacks(runnable)
    }


}