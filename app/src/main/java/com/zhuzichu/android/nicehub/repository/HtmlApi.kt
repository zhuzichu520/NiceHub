package com.zhuzichu.android.nicehub.repository

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface HtmlApi {

    @GET("users/{login}/contributions")
    fun getContributions(
        @Path("login") login: String
    ): Flowable<String>

    @GET("{login}/{name}")
    fun getRepository(
        @Path("login") login: String,
        @Path("name") name: String
    ): Flowable<String>
}