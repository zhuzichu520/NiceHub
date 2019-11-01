package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.nicehub.BuildConfig
import com.zhuzichu.android.nicehub.repository.RemoteRepository
import com.zhuzichu.android.nicehub.repository.RemoteRepositoryImpl
import com.zhuzichu.android.shared.storage.GlobalStorage
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("GlobalParamter") paramterInterceptor: Interceptor,
        @Named("HttpLog") httpLogInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLogInterceptor)
            .addInterceptor(paramterInterceptor)
            .connectTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .readTimeout(15L, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    @Named("GithubApp")
    fun provideGithubAppRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.HOST_APP2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("GithubHtml")
    fun provideGithubHtmlRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.HOST_HTML)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("GlobalParamter")
    fun providesGlobalParamterInterceptor(globalStorage: GlobalStorage): Interceptor {
        return Interceptor {
            var request = it.request()
            globalStorage.token?.apply {
                val auth = "token $this"
                request = request.newBuilder().addHeader("Authorization", auth)
                    .build()
            }
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("HttpLog")
    fun providesHttpLogInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}