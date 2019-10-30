package com.zhuzichu.android.nicehub.di

import android.content.Context
import com.zhuzichu.android.nicehub.ApplicationNiceHub
import com.zhuzichu.android.shared.storage.GlobalStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: ApplicationNiceHub): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesGlobalStorage(): GlobalStorage = GlobalStorage()

}