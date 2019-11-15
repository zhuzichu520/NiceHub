package com.zhuzichu.android.nicehub

import androidx.appcompat.app.AppCompatDelegate
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.zhuzichu.android.mvvm.MvvmManager
import com.zhuzichu.android.nicehub.di.DaggerAppComponent
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
            BuildConfig.UMENG_APPKEY,
            BuildConfig.UMENG_CHANNEL,
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        )
        RxJavaPlugins.setErrorHandler {

        }
        MvvmManager.animBuilder = {
            enter=R.anim.slide_in_right
            exit=R.anim.slide_out_left
            popEnter=R.anim.slide_in_left
            popExit=R.anim.slide_out_right
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}