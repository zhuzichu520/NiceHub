package com.zhuzichu.android.nicehub.repository

import androidx.annotation.NonNull
import com.zhuzichu.android.nicehub.repository.entity.BeanAuthor
import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanUser
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {
    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Flowable<BeanListRes<BeanRepository>>

    @POST("authorizations")
    @Headers("Accept: application/json")
    fun authorizations(
        @NonNull @Body paramterAuthorizations: ParamterAuthorizations,
        @Header("Authorization") basicToken: String
    ): Flowable<BeanAuthor>

    @GET("user")
    fun getPersonInfo(): Flowable<BeanUser>
}