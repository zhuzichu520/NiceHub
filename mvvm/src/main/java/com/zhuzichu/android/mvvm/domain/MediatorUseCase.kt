package com.zhuzichu.android.mvvm.domain

import androidx.lifecycle.MediatorLiveData

abstract class MediatorUseCase<in P, R> {

    protected val result = MediatorLiveData<Result<R>>()

    open fun observe(): MediatorLiveData<Result<R>> {
        return result
    }

    abstract fun execute(parameters: P)
    
}