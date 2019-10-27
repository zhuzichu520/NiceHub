package com.zhuzichu.android.nicehub

import com.zhuzichu.android.nicehub.di.DaggerAppComponent
import com.zhuzichu.android.shared.global.AppGlobal
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class ApplicationNiceHub : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AppGlobal.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}