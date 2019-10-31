package com.zhuzichu.android.nicehub.di

import android.content.Context
import com.zhuzichu.android.nicehub.ApplicationNiceHub
import com.zhuzichu.android.nicehub.db.AppDatabase
import com.zhuzichu.android.nicehub.db.DaoUser
import com.zhuzichu.android.nicehub.manager.UserManager
import com.zhuzichu.android.nicehub.repository.LocalRepository
import com.zhuzichu.android.nicehub.repository.LocalRepositoryImpl
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.RemoteRepositoryImpl
import com.zhuzichu.android.shared.storage.GlobalStorage
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
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

    @Singleton
    @Provides
    fun providesUserManager(): UserManager = UserManager()

    @Provides
    @Singleton
    fun providesRemoteRepository(retrofit: Retrofit): RemoteRepository {
        return RemoteRepositoryImpl(retrofit)
    }

    @Provides
    @Singleton
    fun providesLocalRepository(daoUser: DaoUser): LocalRepository {
        return LocalRepositoryImpl(daoUser)
    }

}