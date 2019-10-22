package com.zhuzichu.android.nicehub.repository

import com.zhuzichu.android.nicehub.repository.entity.BeanListRes
import com.zhuzichu.android.nicehub.repository.entity.BeanRepository
import io.reactivex.Flowable
import retrofit2.Retrofit

interface RemoteRepository {
    fun getHotRepos(
        query: String,
        page: Int
    ): Flowable<BeanListRes<BeanRepository>>
}

class RemoteRepositoryImpl(
    private val retrofit: Retrofit
) : RemoteRepository {

    private val githubApi by lazy { retrofit.create(GithubApi::class.java) }

    override fun getHotRepos(query: String, page: Int): Flowable<BeanListRes<BeanRepository>> {
        return githubApi.searchRepositories(query, "stars", "desc", page, 20)
    }
}