package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import io.reactivex.Single
import retrofit2.Retrofit

interface RemoteRepository {
    fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Single<BeanListRes<BeanRepository>>
}

class RemoteRepositoryImpl(
    private val retrofit: Retrofit
) : RemoteRepository {

    private val githubApi by lazy { retrofit.create(GithubApi::class.java) }

    override fun getHotRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Single<BeanListRes<BeanRepository>> {
        return githubApi.searchRepositories(query, "stars", "desc", page, pageSize)
    }
}