package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.repository.entity.BeanAuthor
import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.repository.entity.BeanUser
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.http.GET

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
}

class RemoteRepositoryImpl(
    private val retrofit: Retrofit
) : RemoteRepository {

    private val githubApi by lazy { retrofit.create(GithubApi::class.java) }

    override fun getPersonInfo(): Flowable<BeanUser> {
        return githubApi.getPersonInfo()
    }

    override fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Flowable<BeanListRes<BeanRepository>> {
        return githubApi.searchRepositories(query, "stars", "desc", page, pageSize)
    }

    override fun authorizations(
        paramterAuthorizations: ParamterAuthorizations,
        basicToken: String
    ): Flowable<BeanAuthor> {
        return githubApi.authorizations(paramterAuthorizations, basicToken)
    }

}