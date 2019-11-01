package com.zhuzichu.android.nicehub.repository

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface HtmlApi {

    @GET("users/{login}/contributions")
    fun getContributions(
        @Path("login") login: String
    ): Flowable<String>
}