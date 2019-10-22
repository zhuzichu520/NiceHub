package com.zhuzichu.android.nicehub.di

import android.content.Context
import com.zhuzichu.android.nicehub.ApplicationNiceHub
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: ApplicationNiceHub): Context {
        return application.applicationContext
    }
}