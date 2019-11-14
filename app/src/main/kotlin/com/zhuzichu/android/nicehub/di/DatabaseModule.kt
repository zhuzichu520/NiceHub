package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.nicehub.db.AppDatabase
import com.zhuzichu.android.nicehub.db.DaoUser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(): AppDatabase = AppDatabase.getInstance()

    @Singleton
    @Provides
    fun providesDaoUser(appDatabase: AppDatabase): DaoUser = appDatabase.daoUser()

}