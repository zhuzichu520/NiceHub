package com.zhuzichu.android.nicehub.repository

import androidx.annotation.NonNull
import com.zhuzichu.android.nicehub.repository.entity.*
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import javax.inject.Named

interface RemoteRepository {
    fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Flowable<BeanListRes<BeanRepository>>

    fun authorizations(
        paramterAuthorizations: ParamterAuthorizations,
        basicToken: String
    ): Flowable<BeanAuthor>


    fun getUserInfo(
        token: String?
    ): Flowable<BeanUser>

    fun getContributions(
        login: String
    ): Flowable<String>

    fun getRepositoryReadme(
        login: String,
        name: String
    ): Flowable<String>

    fun searchRepositories(
        query: String,
        sort: String,
        order: String,
        page: Int,
        perPage: Int
    ): Flowable<BeanListRes<BeanRepository>>

    fun getFollowers(
        login: String,
        follower: String,
        page: Int,
        perPage: Int
    ): Flowable<List<BeanFollower>>

    fun getFiles(
        login: String,
        name: String,
        path: String,
        ref: String
    ): Flowable<List<BeanFile>>

    fun getFollowStatus(
        login: String
    ): Flowable<Boolean>

    fun followUser(
        login: String
    ): Flowable<Boolean>

    fun unfollowUser(
        login: String
    ): Flowable<Boolean>

    fun getUser(
        login: String
    ): Flowable<BeanUser>
}

class RemoteRepositoryImpl(
    @Named("gsonRetrofit")
    private val gsonRetrofit: Retrofit,
    @Named("scalarsRetrofit")
    private val scalarsRetrofit: Retrofit,
    @Named("htmlRetrofit")
    private val htmlRetrofit: Retrofit
) : RemoteRepository {

    override fun getUser(login: String): Flowable<BeanUser> {
        return app.getUser(login)
    }

    override fun getFollowStatus(login: String): Flowable<Boolean> {
        return scalars.getFollowStatus(login)
    }

    override fun followUser(login: String): Flowable<Boolean> {
        return app.followUser(login)
    }

    override fun unfollowUser(login: String): Flowable<Boolean> {
        return app.unfollowUser(login)
    }

    override fun getFiles(
        login: String,
        name: String,
        path: String,
        ref: String
    ): Flowable<List<BeanFile>> {
        return app.getFiles(login, name, path, ref)
    }

    override fun getFollowers(
        login: String,
        follower: String,
        page: Int,
        perPage: Int
    ): Flowable<List<BeanFollower>> {
        return app.getFollowers(follower, login, page, perPage)
    }

    override fun getRepositoryReadme(login: String, name: String): Flowable<String> {
        return html.getRepository(login, name)
    }

    override fun getContributions(login: String): Flowable<String> {
        return html.getContributions(login)
    }

    override fun searchRepositories(
        query: String,
        sort: String,
        order: String,
        page: Int,
        perPage: Int
    ): Flowable<BeanListRes<BeanRepository>> {
        return app.searchRepositories(query, sort, order, page, perPage)
    }

    override fun getUserInfo(token: String?): Flowable<BeanUser> {
        return app.getUserInfo(token)
    }

    override fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Flowable<BeanListRes<BeanRepository>> {
        return app.searchRepositories(query, "stars", "desc", page, pageSize)
    }

    override fun authorizations(
        paramterAuthorizations: ParamterAuthorizations,
        basicToken: String
    ): Flowable<BeanAuthor> {
        return app.authorizations(paramterAuthorizations, basicToken)
    }

    private val app by lazy { gsonRetrofit.create(GithubApi::class.java) }

    private val scalars by lazy { scalarsRetrofit.create(GithubApi::class.java) }

    private val html by lazy { htmlRetrofit.create(HtmlApi::class.java) }

}