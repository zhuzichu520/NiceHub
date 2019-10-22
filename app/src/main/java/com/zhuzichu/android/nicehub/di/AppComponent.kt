package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.mvvm.di.ViewModelModule
import com.zhuzichu.android.nicehub.ApplicationNiceHub
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        NetworkModule::class

    ]
)

interface AppComponent : AndroidInjector<ApplicationNiceHub> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: ApplicationNiceHub): AppComponent
    }
}