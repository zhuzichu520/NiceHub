package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Flowable<BeanListRes<BeanRepository>>
}