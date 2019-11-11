package com.zhuzichu.android.nicehub.repository

import androidx.annotation.NonNull
import com.zhuzichu.android.nicehub.repository.entity.*
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import io.reactivex.Flowable
import io.reactivex.Observable
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
    fun getUserInfo(
        @Header("Authorization") token: String?
    ): Flowable<BeanUser>

    @GET("users/{login}/{follower}")
    fun getFollowers(
        @Path("login") login: String,
        @Path("follower") follower: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Flowable<List<BeanFollower>>

    @GET("repos/{login}/{name}/contents/{path}")
    fun getFiles(
        @Path("login") login: String,
        @Path("name") name: String,
        @Path(value = "path", encoded = true) path: String,
        @Query("ref") ref: String
    ): Flowable<List<BeanFile>>

    @GET("user/following/{login}")
    fun getFollowStatus(
        @Path("login") login: String
    ): Flowable<Boolean>

    @PUT("user/following/{login}")
    fun followUser(
        @Path("login") login: String
    ): Flowable<Boolean>

    @DELETE("user/following/{login}")
    fun unfollowUser(
        @Path("login") login: String
    ): Flowable<Boolean>

    @GET("users/{login}")
    fun getUser(
        @Path("login") username: String
    ): Flowable<BeanUser>

}