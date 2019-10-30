package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.nicehub.BuildConfig
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.RemoteRepositoryImpl
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.storage.GlobalStorage
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("GlobalParamterInterceptor") paramterInterceptor: Interceptor,
        @Named("HttpLogInterceptor") httpLogInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLogInterceptor)
            .addInterceptor(paramterInterceptor)
            .retryOnConnectionFailure(false)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.HOST_APP2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesRepository(retrofit: Retrofit): RemoteRepository {
        return RemoteRepositoryImpl(retrofit)
    }


    @Provides
    @Singleton
    @Named("GlobalParamterInterceptor")
    fun providesGlobalParamterInterceptor(globalStorage: GlobalStorage): Interceptor {
        return Interceptor {
            var request = it.request()
            globalStorage.token?.apply {
                val auth = if (this.startsWith("Basic")) this else "token $this"
                auth.logi("ahahahaha")
                request = request.newBuilder().addHeader("Authorization", auth)
                    .build()
            }
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("HttpLogInterceptor")
    fun providesHttpLogInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}