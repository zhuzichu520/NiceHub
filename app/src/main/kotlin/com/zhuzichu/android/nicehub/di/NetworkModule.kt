package com.zhuzichu.android.nicehub.di

import com.zhuzichu.android.nicehub.BuildConfig
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
        @Named("paramterInterceptor") paramterInterceptor: Interceptor,
        @Named("httpLogInterceptor") httpLogInterceptor: Interceptor
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
    @Named("gsonRetrofit")
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
    @Named("htmlRetrofit")
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
    @Named("paramterInterceptor")
    fun providesGlobalParamterInterceptor(globalStorage: GlobalStorage): Interceptor {
        return Interceptor {
            var request = it.request()
            globalStorage.token?.apply {
                request = request.newBuilder().addHeader("Authorization", this)
                    .build()
            }
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("httpLogInterceptor")
    fun providesHttpLogInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}