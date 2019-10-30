package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import com.zhuzichu.android.nicehub.ui.account.login.entity.ParamterAuthorizations
import io.reactivex.Flowable
import retrofit2.Retrofit

interface RemoteRepository {
    fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Flowable<BeanListRes<BeanRepository>>

    fun authorizations(
        paramterAuthorizations: ParamterAuthorizations
    ): Flowable<String>
}

class RemoteRepositoryImpl(
    private val retrofit: Retrofit
) : RemoteRepository {

    private val githubApi by lazy { retrofit.create(GithubApi::class.java) }

    override fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Flowable<BeanListRes<BeanRepository>> {
        return githubApi.searchRepositories(query, "stars", "desc", page, pageSize)
    }

    override fun authorizations(paramterAuthorizations: ParamterAuthorizations): Flowable<String> {
        return githubApi.authorizations(paramterAuthorizations)
    }

}