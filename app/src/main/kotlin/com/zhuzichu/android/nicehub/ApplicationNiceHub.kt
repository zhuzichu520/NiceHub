package com.zhuzichu.android.nicehub

import androidx.appcompat.app.AppCompatDelegate
import com.umeng.commonsdk.UMConfigure
import com.zhuzichu.android.mvvm.MvvmManager
import com.zhuzichu.android.nicehub.di.DaggerAppComponent
import com.zhuzichu.android.nicehub.extension.toAnimationBuild
import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.shared.storage.GlobalStorage
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

class ApplicationNiceHub : DaggerApplication() {

    @Inject
    lateinit var globalStorage: GlobalStorage

    override fun onCreate() {
        super.onCreate()
        AppGlobal.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppCompatDelegate.setDefaultNightMode(globalStorage.uiMode)

        UMConfigure.init(
            this,
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        )
        RxJavaPlugins.setErrorHandler {

        }
        MvvmManager.animBuilder = globalStorage.animation.toAnimationBuild()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}