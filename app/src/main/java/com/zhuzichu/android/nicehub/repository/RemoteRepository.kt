package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.repository.entity.BeanAuthor
import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanUser
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.http.GET
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


    fun getPersonInfo(): Flowable<BeanUser>

    fun getContributions(
        login: String
    ): Flowable<String>

    fun searchRepositories(
        query: String,
        sort: String,
        order: String,
        page: Int,
        perPage: Int
    ): Flowable<BeanListRes<BeanRepository>>
}

class RemoteRepositoryImpl(
    @Named("GithubApp")
    private val githubAppRetrofit: Retrofit,
    @Named("GithubHtml")
    private val githubHtmlRetrofit: Retrofit
) : RemoteRepository {

    private val app by lazy { githubAppRetrofit.create(GithubApi::class.java) }

    private val html by lazy { githubHtmlRetrofit.create(HtmlApi::class.java) }

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

    override fun getPersonInfo(): Flowable<BeanUser> {
        return app.getPersonInfo()
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

}